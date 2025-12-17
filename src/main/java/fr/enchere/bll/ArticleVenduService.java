package fr.enchere.bll;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.ArticleVenduDto;

import java.util.List;

public interface ArticleVenduService {

    ArticleVendu findArticleById(int id);
    List<ArticleVenduDto> AfficherListeArticleVendu();

    List <ArticleVenduDto> AfficherlisteArticleVenduByNom(String recherche);

    List<ArticleVenduDto> AfficherListeArticleVenduFiltree(int no_categorie, String recherche);

    Void creerArticle(ArticleVendu articleVendu, Retrait retrait, Utilisateur utilisateur);
}
