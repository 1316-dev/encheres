package fr.enchere.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleVendu {
    private int noArticle;
    private String nomArticle;
    private String description;
    private LocalDateTime dateDebutEnchere;
    private LocalDateTime dateFinEnchere;
    private int miseAPrix;
    private int prixVente;
    private boolean etatVente;

    private Utilisateur utilisateur;
    private Retrait lieuRetrait;
    private List<Enchere> listeEncheres;

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
    //Getters et setters

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
        return lieuRetrait;
    }

    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public List<Enchere> getEncheres() {
        return listeEncheres;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.listeEncheres = encheres;
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
                ", utilisateur=" + utilisateur +
                ", lieuRetrait=" + lieuRetrait +
                ", encheres=" + listeEncheres +
                '}';
    }
}
