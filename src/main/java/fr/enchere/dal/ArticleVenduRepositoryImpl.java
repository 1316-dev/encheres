package fr.enchere.dal;

import fr.enchere.bo.ArticleVendu;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleVenduRepositoryImpl implements ArticleVenduRepository{

    private JdbcTemplate jdbcTemplate;

    public ArticleVenduRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ArticleVendu> listeArticleVendu() {
        return List.of();
    }
}
