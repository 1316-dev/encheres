package fr.enchere.controller;

import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.UtilisateurDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UtilisateurController {

   @GetMapping({"/connexion"})
    public String connexion() {
        return "view-connexion";
    }

    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService=utilisateurService;
    }


    @GetMapping({"/inscription"})
    public String inscription(Model model) {
    UtilisateurDto utilisateurDto=(UtilisateurDto) model.getAttribute("utilisateurDto");
            if(utilisateurDto==null){
                model.addAttribute("utilisateurDto", new UtilisateurDto());
            }
        return "view-creer-compte";

    }
   @PostMapping({"/inscription"})
    public String creerUtilisateur (@Valid UtilisateurDto utilisateurDto, BindingResult resultat, RedirectAttributes redirectAttr){
       System.out.println("je suis ici : " + utilisateurDto);
        if(resultat.hasErrors()){
            redirectAttr.addFlashAttribute( "org.springframework.validation.BindingResult.utilisateurDto", resultat);
            redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);
            return "redirect:/view-list-encheres";
        }
        Utilisateur utilisateur= new Utilisateur();
        BeanUtils.copyProperties(utilisateurDto, utilisateur);
        System.out.println("je suis la : " + utilisateur);
       utilisateurService.creerUtilisateur(utilisateur);
                return "redirect:/view-list-encheres?pseudo=" + utilisateur.getPseudo();
    }

}
