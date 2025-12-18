package fr.enchere.dal;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Utilisateur;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


@Repository
public class EnchereRepositoryImpl implements EnchereRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public EnchereRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Enchere consulterEnchereParId(int id) {
        String sql = "select no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur from ENCHERES where no_enchere = ?";
        Enchere enchere = jdbcTemplate.queryForObject(sql,new EnchereRepositoryImpl.EnchereRowMapper(),id);
        return enchere;

    }

    @Override
    public void creerEnchere(Enchere enchere) {

        String sql = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur VALUES(:date_enchere, :montant_enchere, :no_article, :no_utilisateur)";

        KeyHolder keyholder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("date_enchere",enchere.getDateEnchere());
        parameters.addValue("montant_enchere", enchere.getMontantEnchere());
        parameters.addValue("no_article", enchere.getArticleVendu().getNoArticle());
        parameters.addValue("no_utilisateur", enchere.getUtilisateur().getNoUtilisateur());

        namedParameterJdbcTemplate.update(sql, parameters, keyholder, new String[]{"no_article"});
        enchere.setNoEnchere(keyholder.getKey().intValue());
    }

    // Création d'une classe interne pour faire notre mapping
    class EnchereRowMapper implements RowMapper<Enchere> {

        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enchere enchere = new Enchere();
            ArticleVendu articleVendu = new ArticleVendu();
            Utilisateur utilisateur = new Utilisateur();

            enchere.setNoEnchere(rs.getInt("no_enchere"));
            enchere.setDateEnchere(rs.getObject("date_enchere", LocalDateTime.class));
            enchere.setMontantEnchere(rs.getInt("montant_enchere"));
            articleVendu.setNoArticle(rs.getInt("no_article"));
            utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));

            enchere.setArticleVendu(articleVendu);
            enchere.setUtilisateur(utilisateur);

            return enchere;
        }
    }
}
