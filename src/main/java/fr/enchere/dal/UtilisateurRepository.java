package fr.enchere.dal;

import fr.enchere.bo.Utilisateur;

public interface UtilisateurRepository {

    Utilisateur findUserByUsername(String pseudo);
    Utilisateur findUserById(int id);
    Utilisateur findUserByUsernameOrEmail(String email, String pseudo);

    void saveUtilisateur(Utilisateur utilisateur);

   void updateUtilisateur(Utilisateur utilisateur);

    void deleteUtilisateur(int noUtilisateur);

    boolean existsByEmail(String email);
    boolean existsByPseudo(String pseudo);
}
