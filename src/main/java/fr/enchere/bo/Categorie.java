package fr.enchere.bo;

import java.util.Objects;

public class Categorie {
    private int noCategorie ;
    private String libelle ;

    public Categorie() {
    }

    public Categorie(int noCategorie, String libelle) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
    }

    public int getNoCategorie() {
        return noCategorie;
    }
    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return noCategorie == categorie.noCategorie && Objects.equals(libelle, categorie.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noCategorie, libelle);
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "noCategorie=" + noCategorie +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
