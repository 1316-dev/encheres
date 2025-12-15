package fr.enchere.bo;

import java.util.Objects;

public class Retrait {
    private String rue;
    private String cp;
    private String ville;
    private int noArticle;

    public Retrait() {
    }

    public Retrait(String rue, String cp, String ville, int noArticle) {
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.noArticle = noArticle;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Retrait retrait = (Retrait) o;
        return noArticle == retrait.noArticle && Objects.equals(rue, retrait.rue) && Objects.equals(cp, retrait.cp) && Objects.equals(ville, retrait.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, cp, ville, noArticle);
    }


    @Override
    public String toString() {
        return "Retrait{" +
                "rue='" + rue + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                ", noArticle=" + noArticle +
                '}';
    }
}
