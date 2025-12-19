package fr.enchere.bll;

import fr.enchere.bo.Enchere;
import fr.enchere.dal.EnchereRepository;
import org.springframework.stereotype.Service;

@Service
public class EnchereServiceImpl implements EnchereService {

    private EnchereRepository enchereRepository;

    public EnchereServiceImpl(EnchereRepository enchereRepository) {
        this.enchereRepository = enchereRepository;
    }
    @Override
    public Enchere findEnchereById(int id) {
        return enchereRepository.consulterEnchereParId(id);
    }

    @Override
    public void creerEnchere(Enchere ench) {
        //Ajouter l'appel à la fonction du repository
        enchereRepository.creerEnchere(ench);
    }
}
