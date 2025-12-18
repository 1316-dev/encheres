package fr.enchere.bo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enchere {

    private int noEnchere;
    private LocalDateTime dateEnchere;
    private int montantEnchere;

    private ArticleVendu articleVendu;
    private Utilisateur utilisateur;


    public Enchere() {
    }

    public Enchere(Utilisateur utilisateur, ArticleVendu articleVendu,
                   int montantEnchere, LocalDateTime dateEnchere, int noEnchere) {
        this.utilisateur = utilisateur;
        this.articleVendu = articleVendu;
        this.montantEnchere = montantEnchere;
        this.dateEnchere = dateEnchere;
        this.noEnchere = noEnchere;
    }

    public Enchere(LocalDateTime dateEnchere, int montantEnchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.articleVendu = articleVendu;
        this.utilisateur = utilisateur;
    }

    public int getNoEnchere() {
        return noEnchere;
    }
    public void setNoEnchere(int noEnchere) {
        this.noEnchere = noEnchere;
    }

    public LocalDateTime getDateEnchere() {
        return dateEnchere;
    }
    public void setDateEnchere(LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }
    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }
    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enchere enchere = (Enchere) o;
        return noEnchere == enchere.noEnchere && montantEnchere == enchere.montantEnchere && Objects.equals(dateEnchere, enchere.dateEnchere) && Objects.equals(articleVendu, enchere.articleVendu) && Objects.equals(utilisateur, enchere.utilisateur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noEnchere, dateEnchere, montantEnchere, articleVendu, utilisateur);
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "noEnchere=" + noEnchere +
                ", dateEnchere=" + dateEnchere +
                ", montantEnchere=" + montantEnchere +
                ", articleVendu=" + articleVendu +
                ", utilisateur=" + utilisateur +
                '}';
    }
}
