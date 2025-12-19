package fr.enchere.bll;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduRepository;
import fr.enchere.dal.EnchereRepository;
import org.springframework.stereotype.Service;

@Service
public class EnchereServiceImpl implements EnchereService {

    private EnchereRepository enchereRepository;
    private ArticleVenduRepository articleVenduRepository;

    public EnchereServiceImpl(EnchereRepository enchereRepository) {
        this.enchereRepository = enchereRepository;
    }
    @Override
    public Enchere findEnchereById(int id) {
        return enchereRepository.consulterEnchereParId(id);
    }

    @Override
    public void creerEnchere(Enchere currentEnchere) {
        ArticleVendu article = currentEnchere.getArticleVendu();
        int currentBid = currentEnchere.getMontantEnchere();
        Utilisateur currentBidder = currentEnchere.getUtilisateur();
        Utilisateur previousBidder = article.getLastEnchere().getUtilisateur();
        Utilisateur vendeur = article.getUtilisateur();

        //Transaction à mettre en place
        //Gestion métier

        if(currentBidder.equals(vendeur)) {
            //Un utilisateur ne peut enchérir sur un article créée par lui
        }
        //Vérifier le credit de l'acheteur
        int creditAcheteur = currentEnchere.getUtilisateur().getCredit();
        if(currentBid > creditAcheteur) {
            //Crédits insuffisants !
        }
        //Récupérer le dernier montant d'enchere sur l'article
        //Vérifier qu'il correspond à l'enchère du précédent bidder?
        int lastPrice = article.getPrixVente();

        //Vérifier le montant de l'enchere VS la dernière enchère
        if(currentBid <= lastPrice) {
            //Pas assez !
        }

        //SI TOUT OK
        //débiter acheteur
        currentBidder.setCredit(creditAcheteur - currentBid);
        //recréditer acheteur précédent
        previousBidder.setCredit(previousBidder.getCredit() + lastPrice);
        //Ajout d'une enchere à la liste d'enchere de l'article
        article.getListeEncheres().add(currentEnchere);
        //Mise à jour du prix de vente
        article.setPrixVente(currentBid);
        //Ajout d'une enchere à la liste d'encheres du currentBidder
        currentBidder.getEnchere().add(currentEnchere);
        //Ajouter l'appel à la fonction du repository
        enchereRepository.creerEnchere(currentEnchere);

    }
}
