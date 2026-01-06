package fr.enchere.dal;


import fr.enchere.bo.Retrait;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RetraitRepositoryImpl implements RetraitRepository{

    private final JdbcTemplate jdbcTemplate;

    public RetraitRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Création d'une classe interne pour faire notre mapping
    class RetraitRowMapper implements RowMapper<Retrait> {

        @Override
        public Retrait mapRow(ResultSet rs, int rowNum) throws SQLException {
            Retrait retrait = new Retrait();
            retrait.setRue(rs.getString("rue"));
            retrait.setCp(rs.getString("code_postal"));
            retrait.setVille(rs.getString("ville"));

            return retrait;
        }
    }



    @Override
    public List<Retrait> findRetraitByNoUtilisateur(int no_utilisateur) {
        String sql = "select DISTINCT rue, code_postal, ville from retraits where no_utilisateur = ?";
        List<Retrait> retraits = jdbcTemplate.query(sql,new RetraitRepositoryImpl.RetraitRowMapper(),no_utilisateur);

        return retraits;

    }

    @Override
    public List<Retrait> findRetraitByNoArticle(int no_article) {
        String sql = "select DISTINCT rue, code_postal, ville from retraits where no_article = ?";
        List<Retrait> retraits = jdbcTemplate.query(sql,new RetraitRepositoryImpl.RetraitRowMapper(),no_article);

        return retraits;

    }
}
