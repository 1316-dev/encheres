package fr.enchere.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class RetraitDto {
    @Pattern(regexp = "^[a-zA-Z0-9 \\u00C0-\\u00FF'-]+$", message = "La rue ne doit pas contenir de caractères spéciaux.")
    @Size(max = 30, message = "La rue doit faire entre 0 et 30 caractères.")
    private String rue;
    @Pattern(regexp = "[a-zA-Z0-9 \\-]{3,10}", message = "Le CP ne doit pas contenir de caractères spéciaux.")
    @Size(max = 15, message = "Le CP doit faire entre 0 et 10 caractères.")
    private String cp;
    @Pattern(regexp = "^[a-zA-Z0-9 \\u00C0-\\u00FF'-]+$", message = "La ville ne doit pas contenir de caractères spéciaux.")
    @Size(max = 30, message = "La ville doit faire entre 0 et 30 caractères.")
    private String ville;
    private int noArticle;
    private int noUtilisateur;

    public RetraitDto() {
    }

    public RetraitDto(String rue, String cp, String ville, int noArticle, int noUtilisateur) {
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.noArticle = noArticle;
        this.noUtilisateur = noUtilisateur;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(int noArticle) {
        this.noArticle = noArticle;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    @Override
    public String toString() {
        return "RetraitDto{" +
                "rue='" + rue + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                ", noArticle=" + noArticle +
                ", noUtilisateur=" + noUtilisateur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RetraitDto that = (RetraitDto) o;
        return noArticle == that.noArticle && noUtilisateur == that.noUtilisateur && Objects.equals(rue, that.rue) && Objects.equals(cp, that.cp) && Objects.equals(ville, that.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, cp, ville, noArticle, noUtilisateur);
    }
}
