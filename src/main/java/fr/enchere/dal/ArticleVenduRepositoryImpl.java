package fr.enchere.dal;


import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Categorie;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dto.ArticleVenduDto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            articleVenduDto.setDateDebutEnchere(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
            articleVenduDto.setDateFinEnchere(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
            articleVenduDto.setVendeur(rs.getString("vendeur"));
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

        List<ArticleVenduDto> ListeArticleFiltreCategorie = jdbcTemplate.query(sql,new ArticleVenduRowMapper(), "%"+recherche+"%");

        return ListeArticleFiltreCategorie;
    }

    @Override
    public List<ArticleVenduDto> listeArticleVenduByVendeur(String pseudoVendeur) {
        String sql = "select * from dbo.afficherVentesEnCours where vendeur = ? and date_fin_encheres > getdate()";
        List<ArticleVenduDto> ListeArticleFiltreParVendeur = jdbcTemplate.query(sql,new ArticleVenduRowMapper(), pseudoVendeur);
        return ListeArticleFiltreParVendeur;
    }

    @Override
    public List<ArticleVenduDto> listeArticleFiltree(int no_categorie, String recherche) {
        String sql = "select * from dbo.afficherVentesEnCours where no_categorie = ? AND nom_article LIKE ? and date_fin_encheres > getdate()";

        List<ArticleVenduDto> ListeArticleFiltreCategorie = jdbcTemplate.query(sql,new ArticleVenduRowMapper(), no_categorie,"%"+recherche+"%");

        return ListeArticleFiltreCategorie;
    }

    @Override
    public List<ArticleVenduDto> listeMesAchats(int acheteurID,int no_categorie, String recherche, String [] choixCheckBoxAchats) {
        List<ArticleVenduDto> listeMesAchatsFiltrees = new ArrayList<>();
        /*String sql = "SELECT * FROM gestionEncheresMesAchats WHERE acheteur = ? AND date_debut_encheres <= getdate() AND date_fin_encheres >= getdate() AND nom_article LIKE ?";
        listeMesAchatsFiltrees = jdbcTemplate.query(sql,new ArticleVenduRowMapper(),acheteurID,"%"+recherche+"%");*/

        if (no_categorie == 1) {
            if (choixCheckBoxAchats != null) {
                for (String valeur : choixCheckBoxAchats) {
                    if (valeur.equals("encheresOuvertes")) {
                        // L'utilisateur a coché "encheresOuvertes"
                        String sql = "select * from dbo.afficherVentesEnCours where nom_article LIKE ? and date_fin_encheres > getdate()";
                        listeMesAchatsFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(), "%"+recherche+"%"));
                    }
                    if (valeur.equals("mesEncheres")) {
                        // L'utilisateur a coché "mesEncheres"
                        String sql = "SELECT * FROM gestionEncheresMesAchats WHERE acheteur = ? AND date_fin_encheres > getdate() AND nom_article LIKE ?";
                        listeMesAchatsFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),acheteurID,"%"+recherche+"%"));
                    }
                    if (valeur.equals("encheresRemportees")) {
                        // L'utilisateur a coché "encheresRemportees"
                        String sql = "SELECT * FROM gestionEncheresMesAchats WHERE acheteur = ? AND date_fin_encheres < getdate() AND nom_article LIKE ?";
                        listeMesAchatsFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),acheteurID,"%"+recherche+"%"));
                    }
                } }else {
                // Aucune case n'est cochée
                String sql = "select * from dbo.gestionEncheresMesAchats where  acheteur = ? AND nom_article LIKE ?";
                listeMesAchatsFiltrees = jdbcTemplate.query(sql,new ArticleVenduRowMapper(),acheteurID,"%"+recherche+"%");
            }
        } else {
            if (choixCheckBoxAchats != null) {
                for (String valeur : choixCheckBoxAchats) {
                    if (valeur.equals("encheresOuvertes")) {
                        // L'utilisateur a coché "encheresOuvertes"
                        String sql = "select * from dbo.afficherVentesEnCours where no_categorie = ? AND nom_article LIKE ? and date_fin_encheres > getdate()";
                        listeMesAchatsFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(), no_categorie,"%"+recherche+"%"));
                    }
                    if (valeur.equals("mesEncheres")) {
                        // L'utilisateur a coché "mesEncheres"
                        String sql = "SELECT * FROM gestionEncheresMesAchats WHERE acheteur = ? AND date_fin_encheres > getdate() AND nom_article LIKE ? AND no_categorie = ?";
                        listeMesAchatsFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),acheteurID,"%"+recherche+"%", no_categorie));
                    }
                    if (valeur.equals("encheresRemportees")) {
                        // L'utilisateur a coché "encheresRemportees"
                        String sql = "SELECT * FROM gestionEncheresMesAchats WHERE acheteur = ? AND date_fin_encheres < getdate() AND nom_article LIKE ? AND no_categorie = ?";
                        listeMesAchatsFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),acheteurID,"%"+recherche+"%", no_categorie));
                    }
                } }
            else {
                String sql = "select * from dbo.gestionEncheresMesVentes where  acheteur = ?  AND nom_article LIKE ? AND no_categorie = ?";
                listeMesAchatsFiltrees = jdbcTemplate.query(sql, new ArticleVenduRowMapper(), acheteurID, "%" + recherche + "%", no_categorie);
            }
        }
        return listeMesAchatsFiltrees;
    }


    @Override
    public List<ArticleVenduDto> listeMesVentes(String vendeur,int no_categorie, String recherche, String [] choixCheckBoxVentes) {

        List<ArticleVenduDto> listeMesVentesFiltrees = new ArrayList<>();

        if (no_categorie == 1) {
            if (choixCheckBoxVentes != null) {
                for (String valeur : choixCheckBoxVentes) {
                    if (valeur.equals("enCours")) {
                        // L'utilisateur a coché "Mes ventes en cours"
                        String sql = "SELECT  * FROM gestionEncheresMesVentes WHERE vendeur = ? AND date_debut_encheres <= getdate() AND date_fin_encheres >= getdate() AND nom_article LIKE ?";
                        listeMesVentesFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),vendeur,"%"+recherche+"%"));
                    }
                    if (valeur.equals("nonDebutees")) {
                        // L'utilisateur a coché "Ventes non débutées"
                        String sql = "SELECT * FROM gestionEncheresMesVentes WHERE vendeur = ? AND date_debut_encheres > getdate() AND nom_article LIKE ?";
                        listeMesVentesFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),vendeur,"%"+recherche+"%"));
                    }
                    if (valeur.equals("terminees")) {
                        // L'utilisateur a coché "Ventes terminées"
                        String sql = "SELECT * FROM gestionEncheresMesVentes WHERE vendeur = ? AND date_fin_encheres < getdate() AND nom_article LIKE ?";
                        listeMesVentesFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),vendeur,"%"+recherche+"%"));
                }
            } }else {
                // Aucune case n'est cochée
                String sql = "select * from dbo.gestionEncheresMesVentes where  vendeur = ? AND nom_article LIKE ?";
                listeMesVentesFiltrees = jdbcTemplate.query(sql,new ArticleVenduRowMapper(),vendeur,"%"+recherche+"%");
            }
        } else {
            if (choixCheckBoxVentes != null) {
                for (String valeur : choixCheckBoxVentes) {
                    if (valeur.equals("enCours")) {
                        // L'utilisateur a coché "Mes ventes en cours"
                        String sql = "SELECT  * FROM gestionEncheresMesVentes WHERE vendeur = ? AND date_debut_encheres <= getdate() AND date_fin_encheres >= getdate() AND nom_article LIKE ? AND no_categorie = ?";
                        listeMesVentesFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),vendeur,"%"+recherche+"%", no_categorie));
                    }
                    if (valeur.equals("nonDebutees")) {
                        // L'utilisateur a coché "Ventes non débutées"
                        String sql = "SELECT * FROM gestionEncheresMesVentes WHERE vendeur = ? AND date_debut_encheres > getdate() AND nom_article LIKE ? AND no_categorie = ?";
                        listeMesVentesFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),vendeur,"%"+recherche+"%", no_categorie));
                    }
                    if (valeur.equals("terminees")) {
                        // L'utilisateur a coché "Ventes terminées"
                        String sql = "SELECT * FROM gestionEncheresMesVentes WHERE vendeur = ? AND date_fin_encheres < getdate() AND nom_article LIKE ? AND no_categorie = ?";
                        listeMesVentesFiltrees.addAll(jdbcTemplate.query(sql,new ArticleVenduRowMapper(),vendeur,"%"+recherche+"%", no_categorie));
                    }
            } }
            else {
                String sql = "select * from dbo.gestionEncheresMesVentes where  vendeur = ?  AND nom_article LIKE ? AND no_categorie = ?";
                listeMesVentesFiltrees = jdbcTemplate.query(sql, new ArticleVenduRowMapper(), vendeur, "%" + recherche + "%", no_categorie);
            }
        }

        return listeMesVentesFiltrees;
    }

    class ArticleBORowMapper implements RowMapper<ArticleVendu> {

        @Override
        public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {

            Utilisateur user = new Utilisateur();
            Utilisateur userGagnant = new Utilisateur();
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
    public ArticleVendu findArticleById(int id) {
        String sql ="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM Articles_Vendus WHERE no_article = ?";

        ArticleVendu article = jdbcTemplate.queryForObject(sql, new ArticleBORowMapper(), id);

        return article;
    }

    @Override
    public boolean existsByIdAndEtatVenteTrue(int id) {

        String sql = "SELECT CASE WHEN EXISTS (SELECT 1 FROM Articles_Vendus WHERE no_article = ? AND etat_vente = 1) THEN 1 ELSE 0 END";
        Integer result = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);

        return result != null && result == 1;
    }

    @Override
    public void updatePrixVente(int idArticle, int prix){
        String  sql = "UPDATE Articles_Vendus SET prix_vente = ? WHERE no_article = ?";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, prix);
                ps.setInt(2, idArticle);
            }
        };

        jdbcTemplate.update(sql, pss);
    }

    @Override
    public void updateEtatVente(int idArticle, boolean etatVente) {
        String sql = "UPDATE Articles_Vendus SET etat_vente = ? WHERE no_article = ?";
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, etatVente ? 1 : 0);
                ps.setInt(2, idArticle);
            }
        };

        jdbcTemplate.update(sql, pss);
    }

    @Override
    public void setGagnant(int idArticle, int idGagnant) {
        String sql = "UPDATE Articles_Vendus SET no_gagnant = ? WHERE no_article = ?";
        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, idGagnant);
                ps.setInt(2, idArticle);
            }
        };

        jdbcTemplate.update(sql, pss);
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

    //test lena

    @Override
    public int countArticlesVendusParUtilisateur(int noUtilisateur) {

        String sql = "select count(*) from ARTICLES_VENDUS WHERE no_utilisateur = ? ";

        return jdbcTemplate.queryForObject(sql, Integer.class, noUtilisateur);


    }
}
