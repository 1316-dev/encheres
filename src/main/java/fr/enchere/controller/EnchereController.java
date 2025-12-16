package fr.enchere.controller;

import fr.enchere.dto.ArticleDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnchereController {

    @GetMapping({"/","/encheres"})
    public String accueil(Model model) {
        if (!(model.containsAttribute("articleDto"))) {
            model.addAttribute("articleDto", new ArticleDto());
        }
        return "encheres";
    }
}
