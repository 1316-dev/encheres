package fr.enchere.bll;

import fr.enchere.bo.Categorie;
import fr.enchere.dal.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService{

    private CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public List<Categorie> findAllCategorie() {
        List<Categorie> listeCategories = categorieRepository.findAllCategorie();
        return listeCategories;
    }

    @Override
    public Categorie findCategorieById(int no_categorie) {
        return categorieRepository.findCategorieById(no_categorie);
    }
}
