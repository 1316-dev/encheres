package fr.enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateurController {

    @GetMapping({"/connexion"})
    public String connexion() {
        return "view-connexion";
    }
}
