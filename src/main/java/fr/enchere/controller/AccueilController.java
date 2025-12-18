package fr.enchere.controller;

import fr.enchere.bll.ArticleVenduService;
import fr.enchere.bo.ArticleVendu;
import fr.enchere.dto.ArticleVenduDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccueilController {

    private final ArticleVenduService articleVenduService;

    public AccueilController(ArticleVenduService articleVenduService) {
        this.articleVenduService = articleVenduService;
    }


    @GetMapping({"/","/encheres"})
    public String accueil(Model model) {
        if (!(model.containsAttribute("articleVenduDto"))) {
            model.addAttribute("articleVenduDto", new ArticleVenduDto());
        }
        List <ArticleVenduDto> listeArticleVendu = articleVenduService.AfficherListeArticleVendu();
        model.addAttribute("listeArticleVendu", listeArticleVendu);

        return "encheres";
    }

    @GetMapping("/encheresCategorie")
    public String filtrerVenteParCategorie(@RequestParam(name = "noCategorie") int no_categorie,@RequestParam(name="lettreRecherche") String lettreRecherche, Model model) {
        if (!(model.containsAttribute("articleVenduDto"))) {
            model.addAttribute("articleVenduDto", new ArticleVenduDto());
        }
        List <ArticleVenduDto> listeArticleVendufiltre = new ArrayList<>();
        if (no_categorie == 1) {
            listeArticleVendufiltre= articleVenduService.AfficherlisteArticleVenduByNom(lettreRecherche);
        } else {
             listeArticleVendufiltre = articleVenduService.AfficherListeArticleVenduFiltree(no_categorie, lettreRecherche);
        }
        model.addAttribute("listeArticleVendu", listeArticleVendufiltre);
        System.out.println(listeArticleVendufiltre);

        return "encheres";
    }

}
