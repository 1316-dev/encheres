package fr.enchere.dto;


import fr.enchere.bo.Retrait;

import java.util.Date;
import java.util.Objects;

public class ArticleVenduDto {

        private int noArticle;
        private String nomArticle;
        private Date dateFinEnchere;
        private int miseAPrix;
        private String vendeur;
        private int noCategorie;
        private String description;
        private Date dateDebutEnchere;
        private Retrait lieuRetrait;
        private int prixVente;
        private String urlImage ;

    public ArticleVenduDto() {
    }

    public ArticleVenduDto(int noArticle, String nomArticle, Date dateFinEnchere, int miseAPrix, String vendeur, int noCategorie, String description, Date dateDebutEnchere, Retrait lieuRetrait, int prixVente, String urlImage) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.vendeur = vendeur;
        this.noCategorie = noCategorie;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.lieuRetrait = lieuRetrait;
        this.prixVente = prixVente;
        this.urlImage = urlImage;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(Date dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
    }

    public Retrait getLieuRetrait() {
        return lieuRetrait;
    }

    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVenduDto that = (ArticleVenduDto) o;
        return noArticle == that.noArticle && miseAPrix == that.miseAPrix && noCategorie == that.noCategorie && prixVente == that.prixVente && Objects.equals(nomArticle, that.nomArticle) && Objects.equals(dateFinEnchere, that.dateFinEnchere) && Objects.equals(vendeur, that.vendeur) && Objects.equals(description, that.description) && Objects.equals(dateDebutEnchere, that.dateDebutEnchere) && Objects.equals(lieuRetrait, that.lieuRetrait);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noArticle, nomArticle, dateFinEnchere, miseAPrix, vendeur, noCategorie, description, dateDebutEnchere, lieuRetrait, prixVente);
    }

    @Override
    public String toString() {
        return "ArticleVenduDto{" +
                "noArticle=" + noArticle +
                ", nomArticle='" + nomArticle + '\'' +
                ", dateFinEnchere=" + dateFinEnchere +
                ", miseAPrix=" + miseAPrix +
                ", vendeur='" + vendeur + '\'' +
                ", noCategorie=" + noCategorie +
                ", description='" + description + '\'' +
                ", debutFinEnchere=" + dateDebutEnchere +
                ", lieuRetrait=" + lieuRetrait +
                ", prixVente=" + prixVente +
                '}';
    }
}
