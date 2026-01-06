package fr.enchere.dto;


import fr.enchere.bo.Retrait;
import jakarta.validation.constraints.*;


import java.time.LocalDateTime;
import java.util.Objects;

public class ArticleVenduDto {

        private int noArticle;
        @Pattern(regexp = "^[a-zA-Z0-9 \\u00C0-\\u00FF]+$", message = "Le titre ne doit pas contenir de caractères spéciaux.")
        @Size(min = 5, max = 30, message = "Le titre doit faire entre 5 et 30 caractères.")
        @NotBlank
        private String nomArticle;

        //@NotNull(message = "La date de début est obligatoire.")
        @Future(message = "La date de fin doit être postérieure à aujourd'hui.")
        private LocalDateTime dateFinEnchere;

        @Min(value=1)
        private int miseAPrix;
        private String vendeur;
        private int noCategorie;
        @Pattern(regexp = "^[^<>]+$", message = "La description ne doit pas contenir de caractères spéciaux.")
        @Size(min = 5, max = 300, message = "La description doit faire entre 5 et 300 caractères.")
        @NotBlank
        private String description;

        //@NotNull(message = "La date de fin est obligatoire.")
        @Future(message = "La date de début doit être postérieure à aujourd'hui.")
        private LocalDateTime dateDebutEnchere;
        private Retrait lieuRetrait;
        private int prixVente;
        private String urlImage ;

    public ArticleVenduDto() {
    }

    public ArticleVenduDto(int noArticle, String nomArticle, LocalDateTime dateFinEnchere, int miseAPrix, String vendeur, int noCategorie, String description, LocalDateTime dateDebutEnchere, Retrait lieuRetrait, int prixVente, String urlImage) {
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

    public LocalDateTime getDateDebutEnchere() {
        return dateDebutEnchere;
    }

    public void setDateDebutEnchere(LocalDateTime dateDebutEnchere) {
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
