package fr.enchere.dal;

import fr.enchere.bo.Categorie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieRepositoryImpl implements CategorieRepository{
    @Override
    public List<Categorie> findAllCategorie() {
        //todo : lien sql
        return List.of();
    }
}
