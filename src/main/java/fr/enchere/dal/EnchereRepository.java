package fr.enchere.dal;

import fr.enchere.bo.Enchere;

public interface EnchereRepository {
    Enchere consulterEnchereParId(int id);
    void creerEnchere(Enchere p);
}
