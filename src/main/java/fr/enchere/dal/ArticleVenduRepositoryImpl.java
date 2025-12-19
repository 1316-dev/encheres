package fr.enchere.dal;


import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.ArticleVenduDto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArticleVenduRepositoryImpl implements ArticleVenduRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public ArticleVenduRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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
            articleVenduDto.setDateFinEnchere(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
            articleVenduDto.setVendeur(rs.getString("Vendeur"));
            articleVenduDto.setNoCategorie(rs.getInt("no_categorie"));


            return articleVenduDto;
        }
    }

    @Override
    public ArticleVendu findArticleById(int id) {
        String sql ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_Vendus WHERE no_article = ?";

        ArticleVendu article = jdbcTemplate.queryForObject(sql, new ArticleBORowMapper(), id);

        return article;
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

    class ArticleBORowMapper implements RowMapper<ArticleVendu> {

        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {

            Utilisateur user = new Utilisateur();
            Categorie categorie = new Categorie();


            ArticleVendu articleVendu = new ArticleVendu();
            articleVendu.setNoArticle(rs.getInt("no_article"));
            articleVendu.setNomArticle(rs.getString("nom_article"));
            articleVendu.setDescription(rs.getString("description"));
            articleVendu.setDateDebutEnchere(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
            articleVendu.setDateFinEnchere(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
            articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
            articleVendu.setPrixVente(rs.getInt("prix_vente"));

            user.setNoUtilisateur(rs.getInt("no_utilisateur"));
            categorie.setNoCategorie(rs.getInt("no_categorie"));

            articleVendu.setUtilisateur(user);
            articleVendu.setCategorieArticle(categorie);

            return articleVendu;
        }
    }

    @Override
    public void createArticle(ArticleVendu articleVendu, Retrait retrait, Utilisateur utilisateur) {
        String sql = "insert into articles_vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,  no_utilisateur, no_categorie)"
                + "values(:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :no_utilisateur, :noCategorie)";

        KeyHolder keyholder = new GeneratedKeyHolder();


        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("nom_article", articleVendu.getNomArticle());
        parameters.addValue("description", articleVendu.getDescription());
        parameters.addValue("date_debut_encheres", articleVendu.getDateDebutEnchere());
        parameters.addValue("date_fin_encheres", articleVendu.getDateFinEnchere());
        parameters.addValue("prix_initial", articleVendu.getMiseAPrix());
        parameters.addValue("no_utilisateur", utilisateur.getNoUtilisateur());
        parameters.addValue("noCategorie", articleVendu.getCategorieArticle().getNoCategorie());

        namedParameterJdbcTemplate.update(sql, parameters, keyholder, new String[]{"no_article"});
        articleVendu.setNoArticle(keyholder.getKey().intValue());

        String sql2 = "insert into retraits(no_article,rue,code_postal,ville,no_utilisateur)" +
                "values(:no_article,:rue,:code_postal,:ville,:no_utilisateur)";



        MapSqlParameterSource parametersRetrait = new MapSqlParameterSource();
        parametersRetrait.addValue("no_article", articleVendu.getNoArticle());
        parametersRetrait.addValue("rue", retrait.getRue());
        parametersRetrait.addValue("code_postal", retrait.getCp());
        parametersRetrait.addValue("ville", retrait.getVille());
        parametersRetrait.addValue("no_utilisateur", utilisateur.getNoUtilisateur());
        namedParameterJdbcTemplate.update(sql2, parametersRetrait);


    }
}
