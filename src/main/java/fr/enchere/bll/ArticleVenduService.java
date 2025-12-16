package fr.enchere.bll;

import fr.enchere.dto.ArticleVenduDto;

import java.util.List;

public interface ArticleVenduService {

    List<ArticleVenduDto> AfficherListeArticleVendu();

    List <ArticleVenduDto> AfficherlisteArticleVenduByNom(String recherche);

    List<ArticleVenduDto> AfficherListeArticleVenduFiltree(int no_categorie, String recherche);
}
