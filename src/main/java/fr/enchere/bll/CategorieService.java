package fr.enchere.bll;

import fr.enchere.bo.Categorie;

import java.util.List;

public interface CategorieService {

    List<Categorie> findAllCategorie();
    Categorie findCategorieById(int no_categorie);
}
