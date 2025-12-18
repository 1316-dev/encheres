package fr.enchere.controller;

import fr.enchere.bll.ArticleVenduService;
import fr.enchere.bll.CategorieService;
import fr.enchere.bll.EnchereService;
import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.*;

import fr.enchere.dal.EnchereRepository;
import fr.enchere.dto.ArticleVenduDto;
import fr.enchere.dto.RetraitDto;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class EnchereController {


    private final UtilisateurService utilisateurService;
    private final ArticleVenduService articleVenduService;
    private final CategorieService categorieService;
    private EnchereService enchereService;

    public EnchereController(UtilisateurService utilisateurService, ArticleVenduService articleVenduService, CategorieService categorieService, EnchereService enchereService) {
        this.utilisateurService = utilisateurService;
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
        this.enchereService = enchereService;
    }

    @GetMapping({"/vendre"})
    public String vendreUnArticle(Model model, Principal principal) {
        ArticleVenduDto articleVenduDto =(ArticleVenduDto) model.getAttribute("articleVenduDto");
        if(articleVenduDto==null){
            model.addAttribute("articleVenduDto", new ArticleVenduDto());

        }
        String pseudo = principal.getName();
        Utilisateur utilisateur = utilisateurService.findUserByUsername(pseudo);
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("utilisateur", utilisateur);

        RetraitDto retraitDto = new RetraitDto();
        retraitDto.setRue(utilisateur.getRue());
        retraitDto.setCp(utilisateur.getCodePostal());
        retraitDto.setVille(utilisateur.getVille());
        model.addAttribute("retraitDto", retraitDto);

        return "view-vendre-article";

    }
    @PostMapping({"/encheres"})
    public String creerArticle(@Valid @ModelAttribute("articleVenduDto") ArticleVenduDto articleVenduDto,
                               BindingResult resultat, // Suit l'article
                               //@Valid @ModelAttribute("retraitDto")
                                   RetraitDto retraitDto,
                               //BindingResult resultatRetrait, // Suit le retrait
                               Model model,
                               Principal principal) {
        //todo valid sur retraitDto
        if (resultat.hasErrors() /*|| resultatRetrait.hasErrors()*/) {

            Utilisateur utilisateur = utilisateurService.findUserByUsername(principal.getName());
            model.addAttribute("utilisateur", utilisateur);


            model.addAttribute("today", LocalDate.now());


            return "view-vendre-article";
        }

        ArticleVendu articleVendu= new ArticleVendu();
        BeanUtils.copyProperties(articleVenduDto, articleVendu);

        Categorie categorie = categorieService.findCategorieById(articleVenduDto.getNoCategorie());

        articleVendu.setCategorieArticle(categorie);

        Retrait retrait= new Retrait();
        BeanUtils.copyProperties(retraitDto, retrait);

        String pseudo = principal.getName();
        Utilisateur utilisateur = utilisateurService.findUserByUsername(pseudo);
        System.out.println(utilisateur);

        articleVenduService.creerArticle(articleVendu, retrait, utilisateur);
        return "redirect:/encheres";
    }

    @GetMapping("/detail-vente/{articleId}")
    public String afficherDetailVente(@PathVariable int articleId, Model model) {
        ArticleVendu article = articleVenduService.findArticleById(articleId);
        model.addAttribute("article", article);
        return "view-details-vente";
    }

    @PostMapping("/detail-vente/{articleId}")
    public String creerEnchere(@PathVariable int articleId, @RequestParam int montant, @AuthenticationPrincipal UserDetails user) {
        //Constructin d'une enchere pour création
        //Initialisation date de l'enchère
        LocalDateTime dateEnchere = LocalDateTime.now();
        //Récupération de l'article
        ArticleVendu article = articleVenduService.findArticleById(articleId);
        //Récupération du créateur de l'enchere connecté
        Utilisateur utilisateur = utilisateurService.findUserByUsername(user.getUsername());

        Enchere enchere = new Enchere(dateEnchere, montant, article, utilisateur);
        System.out.println("créateur de l'enchere : " + enchere.getUtilisateur().getPseudo());
        //enchereService.creerEnchere(enchere);

        return "redirect:/detail-vente/" + articleId;
    }
}
