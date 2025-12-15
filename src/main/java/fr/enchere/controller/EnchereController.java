package fr.enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnchereController {

    @GetMapping({"/","/encheres"})
    public String accueil() {

        return "encheres";
    }
}
