package fr.enchere.controller;

import fr.enchere.bll.ArticleVenduService;
import fr.enchere.bll.UtilisateurService;
import fr.enchere.bo.Utilisateur;

import fr.enchere.dto.ArticleVenduDto;
import fr.enchere.dto.UtilisateurDto;
import fr.enchere.exception.EmailDejaUtiliseException;
import fr.enchere.exception.PseudoDejaUtiliseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class UtilisateurController {


    private final ArticleVenduService articleVenduService;
    private PasswordEncoder passwordEncoder;

    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder, ArticleVenduService articleVenduService) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
        this.articleVenduService = articleVenduService;
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
    public String creerUtilisateur(@Valid UtilisateurDto utilisateurDto, BindingResult resultat,
                                   RedirectAttributes redirectAttr, @RequestParam("confirmation") String confirmation) {


        // Vérification de la correspondance des mots de passe
        if (!utilisateurDto.getMotDePasse().equals(confirmation)) {
            resultat.rejectValue("motDePasse", "motdepasse.diff", "Les mots de passe ne correspondent pas");
        }

        if (resultat.hasErrors()) {
            /* pas utilisé si on remplace redirct part la view
            redirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.utilisateurDto", resultat);
            redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);*/
            return "view-creer-compte";
        }

        try {
            Utilisateur utilisateur = new Utilisateur();
            BeanUtils.copyProperties(utilisateurDto, utilisateur);

            utilisateurService.creerUtilisateur(utilisateur);
            redirectAttr.addFlashAttribute("messageConnexion","Inscription réussie, vous pouvez vous connecter!");
            return "redirect:/connexion";
            // Si OK redirection vers la page des enchères
           // return "redirect:/view-list-encheres?pseudo=" + utilisateurDto.getPseudo();

        } catch (EmailDejaUtiliseException e) {
            resultat.rejectValue("email", "email.existe", "Cet Email est déjà utilisé");

        } catch (PseudoDejaUtiliseException e) {
            resultat.rejectValue("pseudo", "pseudo.existe", "Ce pseudo est déjà utilisé");
        }
        redirectAttr.addFlashAttribute(
                "org.springframework.validation.BindingResult.utilisateurDto", resultat);
        redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);
        return "redirect:/inscription";
    }


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
        utilisateurDto.setCredit(utilisateur.getCredit());

        model.addAttribute("utilisateurDto", utilisateurDto);

        return "view-mon-profil";
    }

    @GetMapping("/modifier")
    public String modifierProfil(Authentication authentication, Model model) {


        if (!model.containsAttribute("utilisateurDto")) {

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
        }

        return "view-modifier-profil";
    }

    @PostMapping("/modifier")
        public String enregistrementModifProfil (
                @ModelAttribute("utilisateurDto") UtilisateurDto utilisateurDto,
                BindingResult resultat,
                RedirectAttributes redirectAttr,
                Authentication authentication,
                HttpServletRequest request){

            Utilisateur utilisateur =
                    utilisateurService.findUserByUsername(authentication.getName());

            boolean pseudoModifie = !utilisateur.getPseudo().equals(utilisateurDto.getPseudo());
            boolean emailModifie = !utilisateur.getEmail().equals(utilisateurDto.getEmail());

            // Unicité pseudo
            if (pseudoModifie && utilisateurService.existsByPseudo(utilisateurDto.getPseudo())) {
                resultat.rejectValue("pseudo", "pseudo.existe", "Ce pseudo est déjà utilisé");
            }

            // Unicité email
            if (emailModifie && utilisateurService.existsByEmail(utilisateurDto.getEmail())) {
                resultat.rejectValue("email", "email.existe", "Cet email est déjà utilisé");
            }

            // Mot de passe actuel obligatoire
            if (utilisateurDto.getMdpActuel() == null || utilisateurDto.getMdpActuel().isBlank()) {
                resultat.rejectValue(
                        "mdpActuel",
                        "mdpActuel.obligatoire",
                        "Le mot de passe actuel est obligatoire"
                );
            }
            // Vérification mot de passe actuel
            else if (!passwordEncoder.matches(
                    utilisateurDto.getMdpActuel(),
                    utilisateur.getMotDePasse())) {

                resultat.rejectValue(
                        "mdpActuel",
                        "mdpActuel.incorrect",
                        "Le mot de passe actuel est incorrect"
                );
            }

            // Nouveau mot de passe (facultatif)
            if (utilisateurDto.getMdpNouveau() != null && !utilisateurDto.getMdpNouveau().isBlank()) {
                if (!utilisateurDto.getMdpNouveau()
                        .equals(utilisateurDto.getMdpConfirmation())) {

                    resultat.rejectValue(
                            "mdpConfirmation",
                            "mdpConfirmation.diff",
                            "La confirmation du mot de passe ne correspond pas"
                    );
                }
            }

            // S'il y a la moindre erreur
            if (resultat.hasErrors()) {
                redirectAttr.addFlashAttribute(
                        "org.springframework.validation.BindingResult.utilisateurDto", resultat);
                redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);
                return "redirect:/modifier";
            }

            // Mise à jour profil
            BeanUtils.copyProperties(utilisateurDto, utilisateur, "noUtilisateur", "motDePasse");

            // Mise à jour du mot de passe si fourni
            if (utilisateurDto.getMdpNouveau() != null && !utilisateurDto.getMdpNouveau().isBlank()) {
                utilisateur.setMotDePasse(
                        passwordEncoder.encode(utilisateurDto.getMdpNouveau())
                );
            }

            utilisateurService.updateProfil(utilisateur);

            // Déconnexion si pseudo modifié
            if (pseudoModifie) {
                request.getSession().invalidate();
                return "redirect:/connexion?pseudoModifie";
            }

            redirectAttr.addFlashAttribute("success", "Profil mis à jour avec succès");
            return "redirect:/monProfil";
        }


        @GetMapping("/profilVendeur/{pseudo}")
        public String profilVendeur (@PathVariable String pseudo, Model model){

            Utilisateur utilisateur =
                    utilisateurService.findUserByUsername(pseudo);
            System.out.println(utilisateur);
            UtilisateurDto utilisateurDto = new UtilisateurDto();
            utilisateurDto.setPseudo(utilisateur.getPseudo());
            utilisateurDto.setVille(utilisateur.getVille());
            model.addAttribute("utilisateurDto", utilisateurDto);

            List<ArticleVenduDto> listeArticleVendeur = articleVenduService.listeArticleVenduByVendeur(pseudo);
            model.addAttribute("listeArticleVendeur", listeArticleVendeur);

            return "view-profil-vendeur";
        }


        @PostMapping("/supprimer")
        public String supprimerCompte(
                @Valid @ModelAttribute("utilisateurDto") UtilisateurDto utilisateurDto,
                BindingResult resultat,
                Authentication authentication,
                HttpServletRequest request,
                RedirectAttributes redirectAttr
        ) {

            // Récupération de l'utilisateur connecté
            Utilisateur utilisateur =
                    utilisateurService.findUserByUsername(authentication.getName());

            // Mot de passe obligatoire
            if (utilisateurDto.getMdpActuel() == null
                    || utilisateurDto.getMdpActuel().isBlank()) {

                resultat.rejectValue(
                        "mdpActuel",
                        "mdpActuel.obligatoire",
                        "Le mot de passe actuel est obligatoire"
                );
            }
            // Vérification mot de passe
            else if (!passwordEncoder.matches(
                    utilisateurDto.getMdpActuel(),
                    utilisateur.getMotDePasse())) {

                resultat.rejectValue(
                        "mdpActuel",
                        "mdpActuel.incorrect",
                        "Le mot de passe actuel est incorrect"
                );
            }

            if (resultat.hasErrors()) {
                redirectAttr.addFlashAttribute(
                        "org.springframework.validation.BindingResult.utilisateurDto",
                        resultat
                );
                redirectAttr.addFlashAttribute("utilisateurDto", utilisateurDto);
                redirectAttr.addFlashAttribute(
                        "error",
                        "Mot de passe requis pour supprimer le compte"
                );
                return "redirect:/modifier";
            }

            if (articleVenduService.utilisateurADesVentes(
                    utilisateur.getNoUtilisateur())) {

                redirectAttr.addFlashAttribute(
                        "error",
                        "Suppression impossible : vous avez des ventes en cours"
                );
                return "redirect:/modifier";
            }

            utilisateurService.supprimerUtilisateur(
                    utilisateur.getNoUtilisateur()
            );

            // Déconnexion
            request.getSession().invalidate();

            redirectAttr.addFlashAttribute(
                    "success",
                    "Votre compte a été supprimé avec succès"
            );

            return "redirect:/encheres";
        }

    }
