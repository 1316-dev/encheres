package fr.enchere.controller;

import ch.qos.logback.core.model.Model;
import fr.enchere.exception.CreditsInsuffisantsException;
import fr.enchere.exception.EnchereTermineeException;
import fr.enchere.exception.EnchereTropBasseException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class EnchereControllerAdvice {

         private static final Logger logger = LoggerFactory.getLogger(EnchereControllerAdvice.class);

        @ExceptionHandler({RuntimeException.class, Exception.class})
        public String handleError (HttpServletRequest req, Exception e) throws Exception {
            logger.error("Erreur interceptée sur l'URL : {} | Message : {}",
                    req.getRequestURL(),
                    e.getMessage(),
                    e);

            return "view-erreur";
        }

    @ExceptionHandler(EnchereTermineeException.class)
    public ResponseEntity<String> handleEnchereTerminee(EnchereTermineeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("L'enchère est terminée");
    }

    @ExceptionHandler(EnchereTropBasseException.class)
    public ResponseEntity<String> handleEnchereTropBasse(EnchereTropBasseException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Le montant proposé est trop bas");
    }

   /* @ExceptionHandler(CreditsInsuffisantsException.class)
   public ResponseEntity<String> handleCreditsInsuffisants(CreditsInsuffisantsException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Crédits insuffisants");
    }*/
   @ExceptionHandler(CreditsInsuffisantsException.class)
   public String handleCreditsInsuffisants(
           CreditsInsuffisantsException ex,
           RedirectAttributes redirectAttributes
   ) {
       redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
       return "redirect:/detail-vente/" + ex.getArticleId();
   }
}
