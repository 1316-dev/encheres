package fr.enchere.bo;

import java.util.Objects;

public class Retrait {
    private String rue;
    private String cp;
    private String ville;

    public Retrait(){

    }
    public Retrait(String rue, String cp, String ville) {
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    //Getters and setters
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

    //To string et equals

    @Override
    public String toString() {
        return "Retrait{" +
                "rue='" + rue + '\'' +
                ", cp='" + cp + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Retrait retrait = (Retrait) o;
        return Objects.equals(rue, retrait.rue) && Objects.equals(cp, retrait.cp) && Objects.equals(ville, retrait.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rue, cp, ville);
    }
}
