package fr.enchere.dto;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Enchere;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UtilisateurDto {

    private int noUtilisateur;
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Uniquement lettres et chiffres")
    @NotBlank
    @Size(min = 3, max = 30, message = "longueur comprise entre  3 et 30" )
    private String pseudo;
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s\\-']+$", message = "Le nom ne doit contenir que des lettres, des espaces ou des tirets.")
    @NotBlank
    @Size(min = 3, max = 30, message = "longueur comprise entre  3 et 30" )
    private String nom;
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s\\-']+$", message = "Le prenom ne doit contenir que des lettres, des espaces ou des tirets.")
    @NotBlank
    @Size(min = 3, max = 30, message = "longueur comprise entre  3 et 30" )
    private String prenom;
    @Email
    @Size(max = 50)
    private String email;
    @Pattern(regexp = "^(\\+|00)?[0-9]{9,15}$", message = "Format de téléphone invalide")    @NotBlank
    @Size(min = 7, max = 15, message = "longueur comprise entre  7 et 15" )
    private String telephone;
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿœŒ\\s\\-']+$", message = "La rue ne doit contenir que des lettres, des espaces ou des tirets.")
    @NotBlank
    @Size(min = 3, max = 30, message = "longueur comprise entre  3 et 30" )
    private String rue;
    @Pattern(regexp = "[a-zA-Z0-9 \\-]{3,10}", message = "Le code postal doit contenir entre 3 et 10 caractères (lettres, chiffres, espaces ou tirets uniquement).")
    @NotBlank
    @Size(min = 3, max = 10, message = "longueur comprise entre  3 et 10" )
    private String codePostal;
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s\\-']+$", message = "Format de ville invalide")
    @Size(min = 2, max = 50, message = "longueur comprise entre  2 et 50" )
    @NotBlank
    private String ville;
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!\\-_])(?=\\S+$).{8,20}$",
            message = "Le mot de passe doit contenir entre 8 et 20 caractères, incluant une majuscule, une minuscule, un chiffre et un caractère spécial."
    )
    private String motDePasse;
    private int credit;
    private boolean administrateur;

    //Ajout pour gestion mdp

    public String getMdpActuel() {
        return mdpActuel;
    }

    public void setMdpActuel(String mdpActuel) {
        this.mdpActuel = mdpActuel;
    }

    public String getMdpNouveau() {
        return mdpNouveau;
    }

    public void setMdpNouveau(String mdpNouveau) {
        this.mdpNouveau = mdpNouveau;
    }

    public String getMdpConfirmation() {
        return mdpConfirmation;
    }

    public void setMdpConfirmation(String mdpConfirmation) {
        this.mdpConfirmation = mdpConfirmation;
    }

    private String mdpActuel;
    private String mdpNouveau;
    private String mdpConfirmation;


    private List<ArticleVendu> articleVendu=new ArrayList<>();
    private List<Enchere>enchere=new ArrayList<>();

    public UtilisateurDto() {
    }

    public UtilisateurDto(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal,
                          String ville, String motDePasse, int credit, boolean administrateur, List<ArticleVendu> articleVendu, List<Enchere> enchere) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
        this.articleVendu = articleVendu;
        this.enchere = enchere;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public List<ArticleVendu> getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(List<ArticleVendu> articleVendu) {
        this.articleVendu = articleVendu;
    }

    public List<Enchere> getEnchere() {
        return enchere;
    }

    public void setEnchere(List<Enchere> enchere) {
        this.enchere = enchere;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurDto that = (UtilisateurDto) o;
        return noUtilisateur == that.noUtilisateur && credit == that.credit && administrateur == that.administrateur && Objects.equals(pseudo, that.pseudo) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(email, that.email) && Objects.equals(telephone, that.telephone) && Objects.equals(rue, that.rue) && Objects.equals(codePostal, that.codePostal) && Objects.equals(ville, that.ville) && Objects.equals(motDePasse, that.motDePasse) && Objects.equals(articleVendu, that.articleVendu) && Objects.equals(enchere, that.enchere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur, articleVendu, enchere);
    }

    @Override
    public String toString() {
        return "UtilisateurDto{" +
                "noUtilisateur=" + noUtilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                ", articleVendu=" + articleVendu +
                ", enchere=" + enchere +
                '}';
    }
}
