package fr.enchere.bll;

import fr.enchere.bo.Retrait;
import fr.enchere.dal.RetraitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetraitServiceImpl implements RetraitService{

   private final RetraitRepository retraitRepository;

    public RetraitServiceImpl(RetraitRepository retraitRepository) {
        this.retraitRepository = retraitRepository;
    }

    @Override
    public List<Retrait> findRetraitByNoUtilisateur(int no_utilisateur) {
        return retraitRepository.findRetraitByNoUtilisateur(no_utilisateur);
    }
}
