package fr.enchere.dal;

import fr.enchere.bo.Categorie;
import fr.enchere.exception.CategorieNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategorieRepositoryImpl implements CategorieRepository{


    private final JdbcTemplate jdbcTemplate;

    public CategorieRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Création d'une classe interne pour faire notre mapping
    class CategorieRowMapper implements RowMapper<Categorie> {

        @Override
        public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categorie categorie = new Categorie();
            categorie.setNoCategorie(rs.getInt("no_categorie"));
            categorie.setLibelle(rs.getString("libelle"));

            return categorie;
        }
    }

    @Override
    public List<Categorie> findAllCategorie() {

        String sql = "select no_categorie, libelle from categories";
        List<Categorie> ListeCategories = jdbcTemplate.query(sql,new CategorieRowMapper());
        return ListeCategories;
    }

    @Override
    public Categorie findCategorieById(int no_categorie) {
        String sql = "select no_categorie, libelle from categories where no_categorie = ?";
        Categorie categorie;

        try {
         categorie = jdbcTemplate.queryForObject(sql,new CategorieRowMapper(),no_categorie);
            } catch (
            EmptyResultDataAccessException ex) {
                throw new CategorieNotFoundException(no_categorie);
            }

        return categorie;
    }
}
