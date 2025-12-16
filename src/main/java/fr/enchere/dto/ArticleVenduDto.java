package fr.enchere.dto;


import java.util.Date;
import java.util.Objects;

public class ArticleVenduDto {

        private int noArticle;
        private String nomArticle;
        private Date dateFinEnchere;
        private int miseAPrix;

        private String vendeur;
        private int noCategorie;


    public ArticleVenduDto() {
    }

    public ArticleVenduDto(int noArticle, String nomArticle, Date dateFinEnchere, int miseAPrix, String vendeur, int noCategorie) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.vendeur = vendeur;
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

    public String getVendeur() {
        return vendeur;
    }

    public void setVendeur(String vendeur) {
        this.vendeur = vendeur;
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
                ", utilisateur=" + vendeur +
                ", categorieArticle=" + noCategorie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVenduDto that = (ArticleVenduDto) o;
        return noArticle == that.noArticle && miseAPrix == that.miseAPrix && noCategorie == that.noCategorie && Objects.equals(nomArticle, that.nomArticle) && Objects.equals(dateFinEnchere, that.dateFinEnchere) && Objects.equals(vendeur, that.vendeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noArticle, nomArticle, dateFinEnchere, miseAPrix, vendeur, noCategorie);
    }
}
