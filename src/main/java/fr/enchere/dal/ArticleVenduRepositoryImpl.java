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
            articleVenduDto.setVendeur(rs.getString("Vendeur"));
            articleVenduDto.setNoCategorie(rs.getInt("no_categorie"));


            return articleVenduDto;
        }
    }

    @Override
    public List<ArticleVenduDto> listeArticleVendu() {
        String sql = "select * from dbo.afficherVentesEnCours where date_fin_encheres > getdate()";

        List<ArticleVenduDto> ListeArticleVenduDto = jdbcTemplate.query(sql,new ArticleVenduRowMapper());
        return ListeArticleVenduDto;
    }

    @Override
    public List<ArticleVenduDto> listeArticleVenduByNom(String recherche) {
        String sql = "select * from dbo.afficherVentesEnCours where nom_article LIKE ? and date_fin_encheres > getdate()";
        System.out.println(sql);
        List<ArticleVenduDto> ListeArticleFiltreCategorie = jdbcTemplate.query(sql,new ArticleVenduRowMapper(), "%"+recherche+"%");

        return ListeArticleFiltreCategorie;
    }

    @Override
    public List<ArticleVenduDto> listeArticleFiltree(int no_categorie, String recherche) {
        String sql = "select * from dbo.afficherVentesEnCours where no_categorie = ? AND nom_article LIKE ? and date_fin_encheres > getdate()";

        List<ArticleVenduDto> ListeArticleFiltreCategorie = jdbcTemplate.query(sql,new ArticleVenduRowMapper(), no_categorie,"%"+recherche+"%");

        return ListeArticleFiltreCategorie;
    }


}
