package fr.enchere.dal;

import fr.enchere.bo.Utilisateur;

public interface UtilisateurRepository {

    Utilisateur findUserByUsername(String pseudo);

    void saveUtilisateur(Utilisateur utilisateur);

    void deleteUtilisateur(int noUtilisateur);
}
