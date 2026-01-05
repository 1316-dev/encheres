package fr.enchere.bll;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduRepository;
import fr.enchere.dto.ArticleVenduDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<ArticleVenduDto> AfficherlisteArticleVenduByNom(String recherche) {
        return articleVenduRepository.listeArticleVenduByNom(recherche);
    }

    @Override
    public List<ArticleVenduDto> AfficherListeArticleVenduFiltree(int no_categorie, String recherche) {
        return articleVenduRepository.listeArticleFiltree(no_categorie,recherche);
    }

    @Override
    public List<ArticleVenduDto> listeArticleVenduByVendeur(String pseudoVendeur) {
        return articleVenduRepository.listeArticleVenduByVendeur(pseudoVendeur);
    }

    @Override
    public void creerArticle(ArticleVendu articleVendu, Retrait retrait, Utilisateur utilisateur) {
        articleVenduRepository.createArticle(articleVendu, retrait,utilisateur);
    }





    @Override
    public List<ArticleVenduDto> GestionMesVentes(String choixRadio, String[] choixCheckBoxVentes, String [] choixCheckBoxAchats, String vendeur, int no_categorie, String recherche, int acheteurID) {

        List<ArticleVenduDto> listeArticleVendufiltre = new ArrayList<>();

        if (choixRadio.equals("achats")) {
            listeArticleVendufiltre = articleVenduRepository.listeMesAchats(acheteurID,no_categorie,recherche, choixCheckBoxAchats);

        } else if (choixRadio.equals("ventes")) {
            listeArticleVendufiltre = articleVenduRepository.listeMesVentes(vendeur,no_categorie,recherche,choixCheckBoxVentes);
        }

        return listeArticleVendufiltre;
    }

    //TEST LENA
    @Override
    public boolean utilisateurADesVentes(int noUtilisateur) {
        return articleVenduRepository.countArticlesVendusParUtilisateur(noUtilisateur) > 0;
    }
}
