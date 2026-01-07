package fr.enchere.bll;

import fr.enchere.bo.Retrait;

import java.util.List;

public interface RetraitService {

    List<Retrait> findRetraitByNoUtilisateur(int no_utilisateur);

    Retrait findRetraitByNoArticle(int no_article);
}
