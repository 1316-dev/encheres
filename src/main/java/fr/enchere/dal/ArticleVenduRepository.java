package fr.enchere.dal;


import fr.enchere.dto.ArticleVenduDto;

import java.util.List;

public interface ArticleVenduRepository {

    List <ArticleVenduDto> listeArticleVendu();
}
