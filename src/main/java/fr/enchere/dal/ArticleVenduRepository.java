package fr.enchere.dal;


import fr.enchere.dto.ArticleVenduDto;

import java.util.List;

public interface ArticleVenduRepository {

    List <ArticleVenduDto> listeArticleVendu();

    List <ArticleVenduDto> listeArticleVenduByCategorie(int no_categorie);

    List <ArticleVenduDto> listeArticleFiltree(int no_categorie, String recherche);

}
