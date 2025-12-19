package fr.enchere.controller;

import fr.enchere.bll.ArticleVenduService;
import fr.enchere.bll.CategorieService;
import fr.enchere.bll.EnchereService;
import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.*;

import fr.enchere.dto.ArticleVenduDto;
import fr.enchere.dto.RetraitDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        ArticleVenduDto articleVenduDto = (ArticleVenduDto) model.getAttribute("articleVenduDto");
        if (articleVenduDto == null) {
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

        ArticleVendu articleVendu = new ArticleVendu();
        BeanUtils.copyProperties(articleVenduDto, articleVendu);

        Categorie categorie = categorieService.findCategorieById(articleVenduDto.getNoCategorie());

        articleVendu.setCategorieArticle(categorie);

        Retrait retrait = new Retrait();
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

    @GetMapping("/gestion-encheres")
    public String listerEnchere(Model model) {
        if (!(model.containsAttribute("articleVenduDto"))) {
            model.addAttribute("articleVenduDto", new ArticleVenduDto());
        }
        List<ArticleVenduDto> listeArticleVendu = articleVenduService.AfficherListeArticleVendu();
        model.addAttribute("listeArticleVendu", listeArticleVendu);

        return "view-gestion-encheres";
    }

    @GetMapping("/encheresFiltrees")
    public String filtrerVenteParCategorie(@RequestParam(name = "noCategorie") int no_categorie, @RequestParam(name = "lettreRecherche") String lettreRecherche, Model model) {
        if (!(model.containsAttribute("articleVenduDto"))) {
            model.addAttribute("articleVenduDto", new ArticleVenduDto());
        }
        List<ArticleVenduDto> listeArticleVendufiltre = new ArrayList<>();
        if (no_categorie == 1) {
            listeArticleVendufiltre = articleVenduService.AfficherlisteArticleVenduByNom(lettreRecherche);
        } else {
            listeArticleVendufiltre = articleVenduService.AfficherListeArticleVenduFiltree(no_categorie, lettreRecherche);
        }
        model.addAttribute("listeArticleVendu", listeArticleVendufiltre);


        return "view-gestion-encheres";
    }

    @PostMapping("/detail-vente/{articleId}")
    public String creerEnchere(@PathVariable int articleId, @RequestParam int montant, @AuthenticationPrincipal UserDetails user, RedirectAttributes redirectAttributes) {
        //Construction d'une enchere pour création
        //Initialisation date de l'enchère
        LocalDateTime dateEnchere = LocalDateTime.now();
        //Récupération de l'article
        ArticleVendu article = articleVenduService.findArticleById(articleId);
        //Récupération du créateur de l'enchere connecté
        Utilisateur utilisateur = utilisateurService.findUserByUsername(user.getUsername());

        Enchere enchere = new Enchere(dateEnchere, montant, article, utilisateur);

        //Gestion des erreurs dans le service
        enchereService.creerEnchere(enchere);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Votre enchère a bien été enregistrée ✔"
        );

        return "redirect:/detail-vente/" + articleId;
    }

    //################################
    // Page Gestion Encheres
    //################################

    @GetMapping("/gestionEncheresFiltrees")
    public String gestionEncheres(@RequestParam(name = "noCategorie") int no_categorie,
                                  @RequestParam(name = "lettreRecherche") String lettreRecherche,
                                  Model model,
                                  HttpServletRequest request,
                                  @RequestParam(name = "typeRecherche", required = false) String typeRecherche,
                                  @RequestParam(name = "groupeVentes", required = false) String[] choixCheckBoxVentes,
                                  Principal principal) {

        if (!(model.containsAttribute("articleVenduDto"))) {
            model.addAttribute("articleVenduDto", new ArticleVenduDto());
        }
        // On récupère la valeur du bouton radio coché

        String choixRadioAchatVente = request.getParameter("typeRecherche");



        choixCheckBoxVentes = request.getParameterValues("groupeVentes");


        String vendeur = principal.getName();

        List<ArticleVenduDto> listeArticleVendufiltre = articleVenduService.GestionMesVentes(choixRadioAchatVente,choixCheckBoxVentes,vendeur,no_categorie,lettreRecherche);

        model.addAttribute("listeArticleVendu", listeArticleVendufiltre);
        model.addAttribute("radioMemorise",choixRadioAchatVente);




        return "view-gestion-encheres";
    }
}


