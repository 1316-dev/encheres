package fr.enchere.bll;

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


}
