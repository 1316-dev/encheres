package fr.enchere.bll;

import fr.enchere.bo.Enchere;
import fr.enchere.bo.Utilisateur;

public interface EnchereService {
    Enchere findEnchereById(int id);
    Enchere findBestEnchere(int id);
    void creerEnchere(Enchere p);
    String findBestBidder(int id);
}
