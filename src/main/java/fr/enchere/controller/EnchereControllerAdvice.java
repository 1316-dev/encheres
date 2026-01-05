package fr.enchere.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
