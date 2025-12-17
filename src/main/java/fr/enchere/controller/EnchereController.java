package fr.enchere.controller;

import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.ArticleVenduDto;
import fr.enchere.dto.RetraitDto;
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
        ArticleVenduDto articleVenduDto =(ArticleVenduDto) model.getAttribute("articleVenduDto");
        if(articleVenduDto==null){
            model.addAttribute("articleVenduDto", new ArticleVenduDto());

        }
        String pseudo = principal.getName();
        Utilisateur utilisateur = utilisateurService.findUserByUsername(pseudo);
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("utilisateur", utilisateur);

        RetraitDto retraitDto = new RetraitDto();
        model.addAttribute("retraitDto", utilisateur);

        return "view-vendre-article";

    }
}
