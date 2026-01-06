package fr.enchere.dal;

import fr.enchere.bo.Retrait;

import java.util.List;

public interface RetraitRepository {

    List<Retrait> findRetraitByNoUtilisateur(int no_utilisateur);

    List<Retrait> findRetraitByNoArticle(int no_article);
}
