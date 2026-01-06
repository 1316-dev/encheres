package fr.enchere.dal;

import fr.enchere.bo.Enchere;

import java.util.List;

public interface EnchereRepository {
    Enchere consulterEnchereParId(int id);
    void creerEnchere(Enchere p);
    List<Enchere> findByProduitId(int id);
    List<Enchere> findByUtilisateurId(int id);
    Enchere findBestEnchere(int id);
}
