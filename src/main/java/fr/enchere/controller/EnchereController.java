package fr.enchere.controller;

import fr.enchere.bll.ArticleVenduService;
import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.ArticleVendu;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class EnchereController {


    private final UtilisateurService utilisateurService;
    private final ArticleVenduService articleVenduService;

    public EnchereController(UtilisateurService utilisateurService, ArticleVenduService articleVenduService) {
        this.utilisateurService = utilisateurService;
        this.articleVenduService = articleVenduService;
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
    @PostMapping({"/encheres"})
    public String creerArtcile(@Valid ArticleVenduDto articleVenduDto,@Valid RetraitDto retraitDto, BindingResult resultat, RedirectAttributes redirectAttr, Principal principal){

        if(resultat.hasErrors()){
            redirectAttr.addFlashAttribute( "org.springframework.validation.BindingResult.utilisateurDto", resultat);
            redirectAttr.addFlashAttribute("articleVenduDto", articleVenduDto);
            return "redirect:/view-list-encheres";
        }

        ArticleVendu articleVendu= new ArticleVendu();
        BeanUtils.copyProperties(articleVenduDto, articleVendu);

        Retrait retrait= new Retrait();
        BeanUtils.copyProperties(retraitDto, retrait);
        String pseudo = principal.getName();
        Utilisateur utilisateur = utilisateurService.findUserByUsername(pseudo);

        articleVenduService.creerArticle(articleVendu, retrait, utilisateur);
        return "redirect:/view-list-encheres";
    }

    @GetMapping("/detail-vente")
    public String afficherDetailVente(@RequestParam(name="id") int id, Model model) {

        ArticleVendu article = articleVenduService.findArticleById(id);

        model.addAttribute("article", article);

        return "view-details-vente";
    }
}
