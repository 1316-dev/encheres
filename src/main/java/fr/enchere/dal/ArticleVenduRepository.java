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

    List <ArticleVenduDto> listeMesAchats(String vendeur,int no_categorie, String recherche);

    List <ArticleVenduDto> listeMesVentesFiltrees(String vendeur,int no_categorie, String recherche);

    List <ArticleVenduDto> listeMesVentes(String vendeur, String recherche);




    ArticleVendu findArticleById(int id);
    void createArticle(ArticleVendu articleVendu, Retrait retrait, Utilisateur utilisateur);
}
