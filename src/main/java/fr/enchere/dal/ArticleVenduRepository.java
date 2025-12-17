package fr.enchere.dal;


import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.ArticleVenduDto;

import java.util.List;

public interface ArticleVenduRepository {

    List <ArticleVenduDto> listeArticleVendu();

    List <ArticleVenduDto> listeArticleVenduByNom(String recherche);

    List <ArticleVenduDto> listeArticleFiltree(int no_categorie, String recherche);

    Void createArticle(ArticleVendu articleVendu, Retrait retrait, Utilisateur utilisateur);

    ArticleVendu findArticleById(int id);
}
