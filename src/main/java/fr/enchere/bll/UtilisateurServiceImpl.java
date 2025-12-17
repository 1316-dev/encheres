package fr.enchere.bll;

import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository=utilisateurRepository;
    }

    @Override
    public void creerUtilisateur(Utilisateur utilisateur){utilisateurRepository.saveUtilisateur(utilisateur);}

    @Override
    public Utilisateur consulterUtilisateur(String pseudo) {
        return utilisateurRepository.findUserByUsername(pseudo);
    }

    @Override
    public void supprimerUtilisateur(int noUtilisateur) {
        utilisateurRepository.deleteUtilisateur(noUtilisateur);
    }

    @Override
    public void updateProfil(Utilisateur utilisateur) {
        utilisateurRepository.updateUtilisateur(utilisateur);
    }
}
