package fr.enchere.controller;

import fr.enchere.dto.UtilisateurDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateurController {


    @GetMapping({"/inscription"})
    public String inscription(Model model) {
    UtilisateurDto utilisateurDto=(UtilisateurDto) model.getAttribute("utilisateurDto");
            if(utilisateurDto==null){
                model.addAttribute("utilisateurDto", new UtilisateurDto());
            }
        return "view-creer-compte";
    }
}
