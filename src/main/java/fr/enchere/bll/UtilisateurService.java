package fr.enchere.bll;

import fr.enchere.bo.Utilisateur;

public interface UtilisateurService {

    void creerUtilisateur(Utilisateur utilisateur);

    Utilisateur findUserByUsername(String pseudo);

}
