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
    public List<ArticleVenduDto> AfficherListeArticleVenduByCategorie(int no_categorie) {
        return articleVenduRepository.listeArticleVenduByCategorie(no_categorie);
    }


}
