package fr.enchere.dal;

import fr.enchere.bo.Categorie;

import java.util.List;

public interface CategorieRepository {

    List<Categorie> findAllCategorie();

    Categorie findCategorieById(int no_categorie);
}
