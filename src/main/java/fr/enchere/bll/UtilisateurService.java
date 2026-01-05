package fr.enchere.bll;

import fr.enchere.bo.Utilisateur;

public interface UtilisateurService {

    void creerUtilisateur(Utilisateur utilisateur);
    Utilisateur findUserByUsername(String pseudo);
    Utilisateur findUserById(int id);
    void supprimerUtilisateur(int noUtilisateur);
    void updateProfil(Utilisateur utilisateur);


    boolean existsByEmail(String email);
    boolean existsByPseudo(String pseudo);
}
