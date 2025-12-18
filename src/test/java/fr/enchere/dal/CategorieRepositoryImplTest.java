package fr.enchere.dal;

import fr.enchere.bo.Categorie;
import fr.enchere.exception.CategorieNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;



import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategorieRepositoryImplTest {

    @Autowired
    private CategorieRepository categorieRepository;

    @Test
    @DisplayName(("test findCategorieById l'id existe"))
    public void testfindCategorieByIdCasNominal(){
        //AAA
        //Arrange : préparation du test
        int id = 3;
        //Act : Appel de la méthode à tester
        Categorie categorie = categorieRepository.findCategorieById(id);

        //Assert : Vérification du résultat du Act
        assertNotNull(categorie);
        assertEquals(id, categorie.getNoCategorie());
    }

    @Test
    @DisplayName(("test findCategorieById l'id n'existe pas"))
    public void testfindGenreByIDExistePas(){
        //Arrange
        int id = 999;
        //Act et Assert avec une lambda
        assertThrows(CategorieNotFoundException.class,()->categorieRepository.findCategorieById(id));
    }


}

