package fr.enchere.dal;

import fr.enchere.bo.Utilisateur;
import fr.enchere.exception.UtilisateurNotFound;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UtilisateurRepositoryImpl implements UtilisateurRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UtilisateurRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate.getJdbcTemplate();;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Utilisateur findUserByUsername(String pseudo) {
        String sql = "select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from utilisateurs where pseudo = ?";
        Utilisateur user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, new UtilisateurRepositoryImpl.UserRowMapper(), pseudo);
        } catch(EmptyResultDataAccessException ex) {
            throw new UtilisateurNotFound("Utilisateur non trouvé");
        }
        return user;
    }

    public class UserRowMapper implements RowMapper<Utilisateur> {
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur user = new Utilisateur();
            user.setPseudo(rs.getString("pseudo"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setEmail(rs.getString("email"));
            user.setTelephone(rs.getString("telephone"));
            user.setRue(rs.getString("rue"));
            user.setCodePostal(rs.getString("code_postal"));
            user.setVille(rs.getString("ville"));
            user.setMotDePasse(rs.getString("mot_de_passe"));
            user.setCredit(rs.getInt("credit"));
            user.setAdministrateur(rs.getBoolean("administrateur"));

            return user;
        }
    }
    @Override
    public void saveUtilisateur(Utilisateur utilisateur){
        String sql ="insert into utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal,  ville, mot_de_passe, credit)"
                    +"values(:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit)";

        KeyHolder keyholder=new GeneratedKeyHolder();


        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("pseudo",utilisateur.getPseudo());
        parameters.addValue("nom",utilisateur.getNom());
        parameters.addValue("prenom",utilisateur.getPseudo());
        parameters.addValue("email",utilisateur.getEmail());
        parameters.addValue("telephone",utilisateur.getTelephone());
        parameters.addValue("rue",utilisateur.getRue());
        parameters.addValue("code_postal",utilisateur.getCodePostal());
        parameters.addValue("ville",utilisateur.getVille());
        parameters.addValue("mot_de_passe",utilisateur.getMotDePasse());
        parameters.addValue("credit",utilisateur.getCredit());

        namedParameterJdbcTemplate.update(sql,parameters, keyholder, new String[]{"no_utilisateur"});
        utilisateur.setNoUtilisateur(keyholder.getKey().intValue());

    }



}
