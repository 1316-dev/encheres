package fr.enchere.dto;

import fr.enchere.bo.Categorie;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ArticleDto {

        private int noArticle;
        private String nomArticle;
        private LocalDateTime dateFinEnchere;
        private int miseAPrix;

        private Utilisateur utilisateur;
        private Categorie categorieArticle;

    public ArticleDto() {
    }

    public ArticleDto(String nomArticle, LocalDateTime dateFinEnchere, int miseAPrix, Utilisateur utilisateur, Categorie categorieArticle) {
        this.nomArticle = nomArticle;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.utilisateur = utilisateur;
        this.categorieArticle = categorieArticle;
    }

    public ArticleDto(int noArticle, String nomArticle, LocalDateTime dateFinEnchere, int miseAPrix, Utilisateur utilisateur, Categorie categorieArticle) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.utilisateur = utilisateur;
        this.categorieArticle = categorieArticle;
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

    public Categorie getCategorieArticle() {
        return categorieArticle;
    }

    public void setCategorieArticle(Categorie categorieArticle) {
        this.categorieArticle = categorieArticle;
    }

    @Override
    public String toString() {
        return "ArticleDto{" +
                "noArticle=" + noArticle +
                ", nomArticle='" + nomArticle + '\'' +
                ", dateFinEnchere=" + dateFinEnchere +
                ", miseAPrix=" + miseAPrix +
                ", utilisateur=" + utilisateur +
                ", categorieArticle=" + categorieArticle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleDto that = (ArticleDto) o;
        return noArticle == that.noArticle && miseAPrix == that.miseAPrix && Objects.equals(nomArticle, that.nomArticle) && Objects.equals(dateFinEnchere, that.dateFinEnchere) && Objects.equals(utilisateur, that.utilisateur) && Objects.equals(categorieArticle, that.categorieArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noArticle, nomArticle, dateFinEnchere, miseAPrix, utilisateur, categorieArticle);
    }
}
