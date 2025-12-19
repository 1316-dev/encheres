package fr.enchere.controller;

import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.Utilisateur;

import fr.enchere.dto.UtilisateurDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    private PasswordEncoder passwordEncoder;

    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder= passwordEncoder;
    }

    @GetMapping({"/connexion"})
    public String connexion() {
        return "view-connexion";
    }

    @GetMapping({"/inscription"})
    public String inscription(Model model) {
        UtilisateurDto utilisateurDto = (UtilisateurDto) model.getAttribute("utilisateurDto");
        if (utilisateurDto == null) {
            model.addAttribute("utilisateurDto", new UtilisateurDto());
        }
        return "view-creer-compte";

    }


    @PostMapping({"/inscription"})
    public String creerUtilisateur(@Valid UtilisateurDto utilisateurDto, BindingResult resultat, RedirectAttributes redirectAttr) {

        if (resultat.hasErrors()) {
            redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.utilisateurDto", resultat);
            redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);
            return "redirect:/view-list-encheres";
        }
        Utilisateur utilisateur = new Utilisateur();
        BeanUtils.copyProperties(utilisateurDto, utilisateur);

        utilisateurService.creerUtilisateur(utilisateur);
        return "redirect:/view-list-encheres?pseudo=" + utilisateur.getPseudo();
    }

    /*@GetMapping("/monProfil")
    public String afficherProfil(@RequestParam(name = "pseudo") String identifiant, Model model) {
        Utilisateur utilisateur = this.utilisateurService.findUserByUsername(identifiant);
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
        return "/view-mon-profil";
    }*/
    @GetMapping("/monProfil")
    public String monProfil(Authentication authentication, Model model) {

        Utilisateur utilisateur =
                utilisateurService.findUserByUsername(authentication.getName());

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

        return "view-mon-profil";
    }
    @GetMapping("/modifier")
    public String modifierProfil(Authentication authentication, Model model) {

        // Récupération de l'utilisateur connecté
        Utilisateur utilisateur = utilisateurService.findUserByUsername(authentication.getName());

        // Création du DTO pour le formulaire
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setPseudo(utilisateur.getPseudo());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setEmail(utilisateur.getEmail());
        utilisateurDto.setTelephone(utilisateur.getTelephone());
        utilisateurDto.setRue(utilisateur.getRue());
        utilisateurDto.setCodePostal(utilisateur.getCodePostal());
        utilisateurDto.setVille(utilisateur.getVille());

        // Ajout du DTO au modèle
        model.addAttribute("utilisateurDto", utilisateurDto);

        return "view-modifier-profil";
    }



    @PostMapping("/modifier")
    public String enregistrementModifProfil(
            @Valid @ModelAttribute UtilisateurDto utilisateurDto,
            BindingResult resultat,
            RedirectAttributes redirectAttr,
            Authentication authentication,
            HttpServletRequest request) {

        if (resultat.hasErrors()) {
            redirectAttr.addFlashAttribute(
                    "org.springframework.validation.BindingResult.utilisateurDto", resultat);
            redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);
            return "redirect:/modifier?pseudo=" + utilisateurDto.getPseudo();
        }

        Utilisateur utilisateur =
                utilisateurService.findUserByUsername(authentication.getName());

        boolean pseudoModifie =
                !utilisateur.getPseudo().equals(utilisateurDto.getPseudo());

        // Copier le profil (hors mot de passe)
        BeanUtils.copyProperties(
                utilisateurDto,
                utilisateur,
                "noUtilisateur", "motDePasse"
        );

       //gestion mdp
        if (utilisateurDto.getMdpActuel() != null && !utilisateurDto.getMdpActuel().isBlank()) {

            if (!passwordEncoder.matches(
                    utilisateurDto.getMdpActuel(),
                    utilisateur.getMotDePasse())) {

                redirectAttr.addFlashAttribute(
                        "error",
                        "Le mot de passe actuel est incorrect"
                );
                return "redirect:/modifier?pseudo=" + utilisateurDto.getPseudo();
            }

            if (utilisateurDto.getMdpNouveau() == null
                    || utilisateurDto.getMdpNouveau().isBlank()) {

                redirectAttr.addFlashAttribute(
                        "error",
                        "Le nouveau mot de passe est obligatoire"
                );
                return "redirect:/modifier?pseudo=" + utilisateurDto.getPseudo();
            }

            if (!utilisateurDto.getMdpNouveau()
                    .equals(utilisateurDto.getMdpConfirmation())) {

                redirectAttr.addFlashAttribute(
                        "error",
                        "La confirmation du mot de passe ne correspond pas"
                );
                return "redirect:/modifier?pseudo=" + utilisateurDto.getPseudo();
            }

            utilisateur.setMotDePasse(
                    passwordEncoder.encode(utilisateurDto.getMdpNouveau())
            );

            redirectAttr.addFlashAttribute(
                    "success",
                    "Mot de passe modifié avec succès"
            );
        } else {
            redirectAttr.addFlashAttribute(
                    "success",
                    "Profil mis à jour"
            );
        }

        utilisateurService.updateProfil(utilisateur);

        if (pseudoModifie) {
            request.getSession().invalidate();
            return "redirect:/connexion?pseudoModifie";
        }

        return "redirect:/monProfil";
    }



    @PostMapping("/supprimer")
    public String supprimerCompte(Authentication authentication,
                                  HttpServletRequest request,
                                  RedirectAttributes redirectAttributes) {

        String pseudo = authentication.getName();
        Utilisateur utilisateur = utilisateurService.findUserByUsername(pseudo);

        utilisateurService.supprimerUtilisateur(utilisateur.getNoUtilisateur());

        request.getSession().invalidate();
        redirectAttributes.addFlashAttribute("success", "Votre compte a été supprimé avec succès");

        return "redirect:/encheres";


    }
}