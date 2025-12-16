package fr.enchere.dal;


import fr.enchere.dto.ArticleVenduDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArticleVenduRepositoryImpl implements ArticleVenduRepository{

    private final JdbcTemplate jdbcTemplate;

    public ArticleVenduRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Création d'une classe interne pour faire notre mapping
    class ArticleVenduRowMapper implements RowMapper<ArticleVenduDto> {

        @Override
        public ArticleVenduDto mapRow(ResultSet rs, int rowNum) throws SQLException {

            ArticleVenduDto articleVenduDto = new ArticleVenduDto();
            articleVenduDto.setNoArticle(rs.getInt("no_article"));
            articleVenduDto.setNomArticle(rs.getString("nom_article"));
            articleVenduDto.setMiseAPrix(rs.getInt("prix_initial"));
            articleVenduDto.setDateFinEnchere(rs.getDate("date_fin_encheres"));
            articleVenduDto.setVendeur(rs.getString("vendeur"));


            return articleVenduDto;
        }
    }

    @Override
    public List<ArticleVenduDto> listeArticleVendu() {
        String sql = "select * from dbo.afficherVentesEnCours";

        List<ArticleVenduDto> ListeArticleVenduDto = jdbcTemplate.query(sql,new ArticleVenduRowMapper());
        return ListeArticleVenduDto;
    }
}
