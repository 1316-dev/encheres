package fr.enchere.dto;

import java.util.Objects;

public class RetraitDto {
    private String rue;
    private String cp;
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
