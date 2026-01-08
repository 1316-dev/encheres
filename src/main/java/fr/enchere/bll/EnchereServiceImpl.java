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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Optional<Enchere> findBestEnchere(int id) {
        return enchereRepository.findBestEnchere(id);
    }

    public Optional<Utilisateur> findBestBidder(int id) {
        return enchereRepository.findBestEnchere(id)
                .map(Enchere::getUtilisateur);
    }


    @Override
    @Transactional
    public void creerEnchere(Enchere currentEnchere) {
        ArticleVendu article = currentEnchere.getArticleVendu();
        int currentBid = currentEnchere.getMontantEnchere();
        Utilisateur currentBidder = currentEnchere.getUtilisateur();

        //récupérer le vendeur de l'article
        Utilisateur vendeur = article.getUtilisateur();

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
            throw new CreditsInsuffisantsException(article.getNoArticle(), "Crédits insuffisants");
        }

        //Récupérer le dernier montant d'enchere sur l'article (int donc non null)
        int lastPrice = article.getPrixVente();

        //Vérifier le montant de l'enchere VS la dernière enchère
        if(currentBid <= lastPrice) {
            System.out.println("Le montant de l'enchère doit dépasser le précédent prix");
            throw new EnchereTropBasseException(article.getNoArticle(),"Le montant proposé est trop bas");
        }


        //Mise à jour du prix de vente
        articleVenduRepository.updatePrixVente(article.getNoArticle(), currentBid);

        //MaJ des credits acheteur
        utilisateurRepository.updateCredits(currentBidder.getNoUtilisateur(), currentBidder.getCredit() - currentBid);

        //Vérification si une enchère précédente existe
        Optional<Enchere> meilleureEnchereOpt = enchereRepository.findBestEnchere(article.getNoArticle());
        if (meilleureEnchereOpt.isPresent()) {
            Enchere ancienneEnchere = meilleureEnchereOpt.get();
            Utilisateur ancienUtilisateur = utilisateurRepository.findUserById(ancienneEnchere.getUtilisateur().getNoUtilisateur());
            //MaJ des crédits ancien acheteur
            utilisateurRepository.updateCredits(ancienUtilisateur.getNoUtilisateur(), ancienUtilisateur.getCredit() + lastPrice);
        }


        //Création de l'enchere via repo
        enchereRepository.creerEnchere(currentEnchere);
    }
}
