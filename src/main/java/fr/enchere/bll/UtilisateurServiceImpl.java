package fr.enchere.bll;

import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.UtilisateurRepository;
import fr.enchere.exception.EmailDejaUtiliseException;
import fr.enchere.exception.PseudoDejaUtiliseException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

    private UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository=utilisateurRepository;
    }

    @Override
    public void creerUtilisateur(Utilisateur utilisateur){
        if (utilisateurRepository.existsByEmail(utilisateur.getEmail())){
            throw new EmailDejaUtiliseException("Cet email est déjà utilisé");
        }
        if (utilisateurRepository.existsByPseudo(utilisateur.getPseudo())) {
            throw new PseudoDejaUtiliseException("Ce pseudo est déjà utilisé");
        }
        utilisateurRepository.saveUtilisateur(utilisateur);}

    @Override
    public Utilisateur findUserByUsername(String pseudo) {
        return utilisateurRepository.findUserByUsername(pseudo);
    }

    @Override
    public Utilisateur findUserById(int id) {
        return utilisateurRepository.findUserById(id);
    }

    @Override
    public void supprimerUtilisateur(int noUtilisateur) {
        utilisateurRepository.deleteUtilisateur(noUtilisateur);
    }

    @Override
    public void updateProfil(Utilisateur utilisateur) {
        utilisateurRepository.updateUtilisateur(utilisateur);
    }
    @Override
    public boolean existsByPseudo(String pseudo) {
        return utilisateurRepository.existsByPseudo(pseudo);
    }

    @Override
    public boolean existsByEmail(String email){
        return utilisateurRepository.existsByEmail(email);
    }

}
