package fr.enchere.bll;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduRepository;
import fr.enchere.dal.EnchereRepository;
import fr.enchere.dal.UtilisateurRepository;
import fr.enchere.exception.CreditsInsuffisantsException;
import fr.enchere.exception.EnchereTermineeException;
import fr.enchere.exception.EnchereTropBasseException;
import org.springframework.stereotype.Service;

@Service
public class EnchereServiceImpl implements EnchereService {

    private EnchereRepository enchereRepository;
    private UtilisateurRepository utilisateurRepository;
    private ArticleVenduRepository articleVenduRepository;

    public EnchereServiceImpl(EnchereRepository enchereRepository, UtilisateurRepository utilisateurRepository, ArticleVenduRepository articleVenduRepository) {
        this.enchereRepository = enchereRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.articleVenduRepository = articleVenduRepository;
    }
    @Override
    public Enchere findEnchereById(int id) {
        return enchereRepository.consulterEnchereParId(id);
    }

    @Override
    public Enchere findBestEnchere(int id) {
        System.out.println("findBestEnchere " + enchereRepository.findBestEnchere(id).getUtilisateur().getNoUtilisateur());
        return enchereRepository.findBestEnchere(id);
    }

    @Override
    public String findBestBidder(int id) {
        Utilisateur bidderNb = enchereRepository.findBestEnchere(id).getUtilisateur();
        Utilisateur bidder = utilisateurRepository.findUserById(bidderNb.getNoUtilisateur());
        return bidder.getPseudo();
    }

    @Override
    public void creerEnchere(Enchere currentEnchere) {
        ArticleVendu article = currentEnchere.getArticleVendu();
        int currentBid = currentEnchere.getMontantEnchere();
        Utilisateur currentBidder = currentEnchere.getUtilisateur();

        //récupérer utilisateur ayant fait l'enchere la plus haute
        Utilisateur vendeur = article.getUtilisateur();
        Utilisateur previousBidder;


        //Transaction à mettre en place
        //Gestion métier
        //Vérifier l'état de vente de l'article (si false, enchère impossible)
        if(article.isEtatVente()){
            System.out.println("Vente cloturée");
           throw new EnchereTermineeException();
        }

        //Un utilisateur ne peut enchérir sur un article créée par lui
        if(currentBidder.getNoUtilisateur() == vendeur.getNoUtilisateur()) {
            System.out.println("Enchere impossible");
        }
        //Vérifier le credit de l'acheteur
        int creditAcheteur = currentBidder.getCredit();
        if(currentBid > creditAcheteur) {
            //Crédits insuffisants !
            System.out.println("Crédits insuffisants");
            throw new CreditsInsuffisantsException();
        }

        //Récupérer le dernier montant d'enchere sur l'article
        int lastPrice = article.getPrixVente();

        //Vérifier le montant de l'enchere VS la dernière enchère
        if(currentBid <= lastPrice) {
            System.out.println("Le montant de l'enchère doit dépasser le précédent prix");
            throw new EnchereTropBasseException();
        }

        //SI TOUT OK
        //Mise à jour du prix de vente
        articleVenduRepository.updatePrixVente(article.getNoArticle(), currentBid);

        //Vérification si une enchère précédente existe
        //Optional à faire ?
        if(!enchereRepository.findByProduitId(article.getNoArticle()).isEmpty())
        {
            Enchere previousEnchere = enchereRepository.findBestEnchere(article.getNoArticle());
            previousBidder = utilisateurRepository.findUserById(previousEnchere.getUtilisateur().getNoUtilisateur());
            //MaJ des crédits ancien acheteur
            utilisateurRepository.updateCredits(previousBidder.getNoUtilisateur(), previousBidder.getCredit() + lastPrice);
        }
        //MaJ des credits acheteur
        utilisateurRepository.updateCredits(currentBidder.getNoUtilisateur(), currentBidder.getCredit() - currentBid);


        //Ajouter l'appel à la fonction du repository
        enchereRepository.creerEnchere(currentEnchere);
    }
}
