package fr.enchere.controller;

import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.UtilisateurDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class EnchereController {


    private final UtilisateurService utilisateurService;

    public EnchereController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping({"/vendre"})
    public String vendreUnArticle(Model model, Principal principal) {
        ArticleVendu articleVendu =(ArticleVendu) model.getAttribute("articleVendu");
        if(articleVendu==null){
            model.addAttribute("articleVendu", new ArticleVendu());

        }
        String pseudo = principal.getName();
        Utilisateur utilisateur = utilisateurService.findUserByUsername(pseudo);
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("utilisateur", utilisateur);

        return "view-vendre-article";

    }
}
