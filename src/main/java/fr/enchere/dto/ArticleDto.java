package fr.enchere.dto;


import fr.enchere.bo.Utilisateur;

import java.time.LocalDateTime;

import java.util.Objects;

public class ArticleDto {

        private int noArticle;
        private String nomArticle;
        private LocalDateTime dateFinEnchere;
        private int miseAPrix;

        private Utilisateur utilisateur;
        private int noCategorie;

    public ArticleDto() {
    }

    public ArticleDto(String nomArticle, LocalDateTime dateFinEnchere, int miseAPrix, Utilisateur utilisateur, int noCategorie) {
        this.nomArticle = nomArticle;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.utilisateur = utilisateur;
        this.noCategorie = noCategorie;
    }

    public ArticleDto(int noArticle, String nomArticle, LocalDateTime dateFinEnchere, int miseAPrix, Utilisateur utilisateur, int noCategorie) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.utilisateur = utilisateur;
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

    public LocalDateTime getDateFinEnchere() {
        return dateFinEnchere;
    }

    public void setDateFinEnchere(LocalDateTime dateFinEnchere) {
        this.dateFinEnchere = dateFinEnchere;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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
                ", utilisateur=" + utilisateur +
                ", categorieArticle=" + noCategorie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDto that = (ArticleDto) o;
        return noArticle == that.noArticle && miseAPrix == that.miseAPrix && noCategorie == that.noCategorie && Objects.equals(nomArticle, that.nomArticle) && Objects.equals(dateFinEnchere, that.dateFinEnchere) && Objects.equals(utilisateur, that.utilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noArticle, nomArticle, dateFinEnchere, miseAPrix, utilisateur, noCategorie);
    }
}
