package fr.enchere.controller;

import fr.enchere.bll.ArticleVenduService;
import fr.enchere.bll.CategorieService;
import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.ArticleVendu;

import fr.enchere.bo.Categorie;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.ArticleVenduDto;
import fr.enchere.dto.RetraitDto;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class EnchereController {


    private final UtilisateurService utilisateurService;
    private final ArticleVenduService articleVenduService;
    private final CategorieService categorieService;

    public EnchereController(UtilisateurService utilisateurService, ArticleVenduService articleVenduService, CategorieService categorieService) {
        this.utilisateurService = utilisateurService;
        this.articleVenduService = articleVenduService;
        this.categorieService = categorieService;
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

    @GetMapping("/detail-vente")
    public String afficherDetailVente(@RequestParam(name="id") int id, Model model) {

        ArticleVendu article = articleVenduService.findArticleById(id);

        model.addAttribute("article", article);

        return "view-details-vente";
    }

    @GetMapping("/gestion-encheres")
    public String listerEnchere(Model model) {
        if (!(model.containsAttribute("articleVenduDto"))) {
            model.addAttribute("articleVenduDto", new ArticleVenduDto());
        }
        List<ArticleVenduDto> listeArticleVendu = articleVenduService.AfficherListeArticleVendu();
        model.addAttribute("listeArticleVendu", listeArticleVendu);

        return "view-gestion-encheres";
    }
}
