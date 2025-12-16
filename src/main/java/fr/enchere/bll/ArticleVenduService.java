package fr.enchere.bll;

import fr.enchere.dto.ArticleVenduDto;

import java.util.List;

public interface ArticleVenduService {

    List<ArticleVenduDto> AfficherListeArticleVendu();

    List <ArticleVenduDto> AfficherListeArticleVenduByCategorie(int no_categorie);
}
