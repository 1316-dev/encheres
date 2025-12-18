package fr.enchere.bll;

import fr.enchere.bo.Enchere;

public interface EnchereService {
    Enchere findEnchereById(int id);
    void creerEnchere(Enchere p);
}
