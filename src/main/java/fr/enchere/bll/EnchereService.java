package fr.enchere.bll;

import fr.enchere.bo.Enchere;
import fr.enchere.bo.Utilisateur;

import java.util.Optional;

public interface EnchereService {
    Enchere findEnchereById(int id);
    Optional<Enchere> findBestEnchere(int id);
    void creerEnchere(Enchere p);
    Optional<Utilisateur> findBestBidder(int id);
}
