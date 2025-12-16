package fr.enchere.dto;


import java.util.Date;
import java.util.Objects;

public class ArticleVenduDto {

        private int noArticle;
        private String nomArticle;
        private Date dateFinEnchere;
        private int miseAPrix;

        private int no_utilisateur;
        private int noCategorie;

    public ArticleVenduDto() {
    }

    public ArticleVenduDto(int noArticle, String nomArticle, Date dateFinEnchere, int miseAPrix, int no_utilisateur, int noCategorie) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.no_utilisateur = no_utilisateur;
        this.noCategorie = noCategorie;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public Date getDateFinEnchere() {
        return dateFinEnchere;
    }

    public void setDateFinEnchere(Date dateFinEnchere) {
        this.dateFinEnchere = dateFinEnchere;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public int getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(int no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public int getNoCategorie() {
        return noCategorie;
    }

    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "noArticle=" + noArticle +
                ", nomArticle='" + nomArticle + '\'' +
                ", dateFinEnchere=" + dateFinEnchere +
                ", miseAPrix=" + miseAPrix +
                ", utilisateur=" + no_utilisateur +
                ", categorieArticle=" + noCategorie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVenduDto that = (ArticleVenduDto) o;
        return noArticle == that.noArticle && miseAPrix == that.miseAPrix && no_utilisateur == that.no_utilisateur && noCategorie == that.noCategorie && Objects.equals(nomArticle, that.nomArticle) && Objects.equals(dateFinEnchere, that.dateFinEnchere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noArticle, nomArticle, dateFinEnchere, miseAPrix, no_utilisateur, noCategorie);
    }
}
