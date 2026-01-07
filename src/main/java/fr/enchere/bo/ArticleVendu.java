package fr.enchere.bo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ArticleVendu {
    private int noArticle;
    private String nomArticle;
    private String description;
    private LocalDateTime dateDebutEnchere;
    private LocalDateTime dateFinEnchere;
    private int miseAPrix;
    private int prixVente;
    private boolean etatVente;

    private Categorie categorieArticle;
    private Utilisateur utilisateur;
    private Retrait lieuRetrait;
    private Utilisateur gagnant;

    public ArticleVendu() {
    }

    public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere, int miseAPrix, int prixVente, boolean etatVente) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
    }
    public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere, int miseAPrix, int prixVente, boolean etatVente) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
    }

    public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere, int miseAPrix, int prixVente, boolean etatVente, Categorie categorieArticle, Utilisateur utilisateur, Retrait lieuRetrait, List<Enchere> listeEncheres) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEnchere = dateDebutEnchere;
        this.dateFinEnchere = dateFinEnchere;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.categorieArticle = categorieArticle;
        this.utilisateur = utilisateur;
        this.lieuRetrait = lieuRetrait;
    }
    //Méthodes


    //Getters et setters


    public Utilisateur getGagnant() {
        return gagnant;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
        this.dateDebutEnchere = dateDebutEnchere;
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

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public boolean isEtatVente() {
        return etatVente;
    }

    public void setEtatVente(boolean etatVente) {
        this.etatVente = etatVente;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Retrait getLieuRetrait() {
        System.out.println("lieur retrait" + lieuRetrait);
        return lieuRetrait;
    }

    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }



    public Categorie getCategorieArticle() {

        return categorieArticle;
    }

    public void setCategorieArticle(Categorie categorieArticle) {
        this.categorieArticle = categorieArticle;
    }



    @Override
    public String toString() {
        return "ArticleVendu{" +
                "noArticle=" + noArticle +
                ", nomArticle='" + nomArticle + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEnchere=" + dateDebutEnchere +
                ", dateFinEnchere=" + dateFinEnchere +
                ", miseAPrix=" + miseAPrix +
                ", prixVente=" + prixVente +
                ", etatVente=" + etatVente +
                ", categorieArticle=" + categorieArticle +
                ", utilisateur=" + utilisateur +
                ", lieuRetrait=" + lieuRetrait +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ArticleVendu that = (ArticleVendu) o;
        return noArticle == that.noArticle && miseAPrix == that.miseAPrix && prixVente == that.prixVente && etatVente == that.etatVente && Objects.equals(nomArticle, that.nomArticle) && Objects.equals(description, that.description) && Objects.equals(dateDebutEnchere, that.dateDebutEnchere) && Objects.equals(dateFinEnchere, that.dateFinEnchere) && Objects.equals(categorieArticle, that.categorieArticle) && Objects.equals(utilisateur, that.utilisateur) && Objects.equals(lieuRetrait, that.lieuRetrait);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noArticle, nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix, prixVente, etatVente, categorieArticle, utilisateur, lieuRetrait);
    }
}
