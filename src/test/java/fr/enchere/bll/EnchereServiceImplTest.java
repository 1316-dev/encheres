package fr.enchere.bll;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class EnchereServiceImplTest {

    @Autowired
    UtilisateurServiceImpl utilisateurService;

    @Autowired
    ArticleVenduServiceImpl articleVenduService;

    @Test
    void testEnchereService(){

        /*Raccord avec script data.sql*/

        //Enchere 1
        Utilisateur user1 = utilisateurService.findUserById(2);
        ArticleVendu article1 = articleVenduService.findArticleById(1);
        LocalDateTime dateTime1 = LocalDateTime.of(
                2025, 12, 20, 12, 6, 59
        );

        Enchere enchere1 = new Enchere(dateTime1,520,article1, user1);
        article1.getListeEncheres().add(enchere1);
        user1.getEnchere().add(enchere1);

        //Enchere 2
        Utilisateur user2 = utilisateurService.findUserById(3);
        ArticleVendu article2 = articleVenduService.findArticleById(1);
        LocalDateTime dateTime2 = LocalDateTime.of(
                2025, 12, 20, 12, 6, 59
        );

        Enchere enchere2 = new Enchere(dateTime2,550,article2, user2);
        article2.getListeEncheres().add(enchere2);
        user2.getEnchere().add(enchere2);

        //Enchere 3
        Utilisateur user3 = utilisateurService.findUserById(1);
        ArticleVendu article3 = articleVenduService.findArticleById(2);
        LocalDateTime dateTime3 = LocalDateTime.of(
                2025, 12, 20, 12, 6, 59
        );

        Enchere enchere3 = new Enchere(dateTime3,220,article3, user3);
        article3.getListeEncheres().add(enchere3);
        user3.getEnchere().add(enchere3);

        //Enchere 4
        Utilisateur user4 = utilisateurService.findUserById(2);
        ArticleVendu article4 = articleVenduService.findArticleById(3);
        LocalDateTime dateTime4 = LocalDateTime.of(
                2025, 12, 20, 12, 6, 59
        );

        Enchere enchere4 = new Enchere(dateTime4,350,article4, user4);
        article4.getListeEncheres().add(enchere4);
        user4.getEnchere().add(enchere4);

    }
}
