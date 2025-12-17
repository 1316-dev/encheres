package fr.enchere.controller;

import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.UtilisateurDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

        if(resultat.hasErrors()){
            redirectAttr.addFlashAttribute( "org.springframework.validation.BindingResult.utilisateurDto", resultat);
            redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);
            return "redirect:/view-list-encheres";
        }
        Utilisateur utilisateur= new Utilisateur();
        BeanUtils.copyProperties(utilisateurDto, utilisateur);

       utilisateurService.creerUtilisateur(utilisateur);
                return "redirect:/view-list-encheres?pseudo=" + utilisateur.getPseudo();
    }

    @GetMapping("/monProfil")
    public String afficherProfil(@RequestParam(name="pseudo")String identifiant,  Model model ){
      Utilisateur  utilisateur = this.utilisateurService.consulterUtilisateur(identifiant);
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setPseudo(utilisateur.getPseudo());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setEmail(utilisateur.getEmail());
        utilisateurDto.setTelephone(utilisateur.getTelephone());
        utilisateurDto.setRue(utilisateur.getRue());
        utilisateurDto.setCodePostal(utilisateur.getCodePostal());
        utilisateurDto.setVille(utilisateur.getVille());
    model.addAttribute("utilisateurDto", utilisateurDto);
    return "/view-mon-profil" ;
    }

    @GetMapping("/modifier")
    public String ModifierProfil(@RequestParam(name="pseudo")String identifiant,  Model model ){
            Utilisateur  utilisateurProfil = this.utilisateurService.consulterUtilisateur(identifiant);
            UtilisateurDto utilisateurDto = new UtilisateurDto();
            utilisateurDto.setPseudo(utilisateurProfil.getPseudo());
            utilisateurDto.setNom(utilisateurProfil.getNom());
            utilisateurDto.setPrenom(utilisateurProfil.getPrenom());
            utilisateurDto.setEmail(utilisateurProfil.getEmail());
            utilisateurDto.setTelephone(utilisateurProfil.getTelephone());
            utilisateurDto.setRue(utilisateurProfil.getRue());
            utilisateurDto.setCodePostal(utilisateurProfil.getCodePostal());
            utilisateurDto.setVille(utilisateurProfil.getVille());
            model.addAttribute("utilisateurDto", utilisateurDto);
        return "/view-modifier-profil";
    }

  
 /* @PostMapping("/modifier")
  public String enregistrementModiProfil(
          @Valid @ModelAttribute UtilisateurDto utilisateurDto,
          BindingResult resultat,
          RedirectAttributes redirectAttr,
          Authentication authentication) {

      if (resultat.hasErrors()) {
          redirectAttr.addFlashAttribute(
                  "org.springframework.validation.BindingResult.utilisateurDto", resultat);
          redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);
          return "redirect:/profil";
      }

      Utilisateur utilisateur =
              utilisateurService.consulterUtilisateur(authentication.getName());

      BeanUtils.copyProperties(utilisateurDto, utilisateur,
              "noUtilisateur", "motDePasse");

      utilisateurService.updateProfil(utilisateur);

      redirectAttr.addFlashAttribute("success", "Profil mis à jour");
      return "redirect:/view-list-encheres";
  }*/

}
