package fr.enchere.bll;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduRepository;
import fr.enchere.dto.ArticleVenduDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService{

    private final ArticleVenduRepository articleVenduRepository;

    public ArticleVenduServiceImpl(ArticleVenduRepository articleVenduRepository) {
        this.articleVenduRepository = articleVenduRepository;
    }

    @Override
    public ArticleVendu findArticleById(int id) {
       return articleVenduRepository.findArticleById(id);
    }

    @Override
    public List<ArticleVenduDto> AfficherListeArticleVendu() {
        return articleVenduRepository.listeArticleVendu();
    }

    @Override
    public List <ArticleVenduDto> AfficherlisteArticleVenduByNom(String recherche) {
        return articleVenduRepository.listeArticleVenduByNom(recherche);
    }

    @Override
    public List<ArticleVenduDto> AfficherListeArticleVenduFiltree(int no_categorie, String recherche) {
        return articleVenduRepository.listeArticleFiltree(no_categorie,recherche);
    }

    @Override
    public List<ArticleVenduDto> AfficherMesVentesFiltrees(String vendeur, int no_categorie, String recherche) {
        return articleVenduRepository.listeMesVentesFiltrees(vendeur,no_categorie,recherche);
    }

    @Override
    public List<ArticleVenduDto> AfficherMesVentes(String vendeur, String recherche) {
        return articleVenduRepository.listeMesVentes(vendeur,recherche);
    }

    @Override
    public void creerArticle(ArticleVendu articleVendu, Retrait retrait, Utilisateur utilisateur) {
        articleVenduRepository.createArticle(articleVendu, retrait,utilisateur);
    }

}
