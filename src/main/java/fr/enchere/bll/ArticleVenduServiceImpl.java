package fr.enchere.bll;

import fr.enchere.bo.ArticleVendu;
import fr.enchere.bo.Enchere;
import fr.enchere.bo.Retrait;
import fr.enchere.bo.Utilisateur;
import fr.enchere.dal.ArticleVenduRepository;
import fr.enchere.dal.EnchereRepositoryImpl;
import fr.enchere.dto.ArticleVenduDto;
import fr.enchere.exception.EnchereTermineeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService{

    private final ArticleVenduRepository articleVenduRepository;
    private final EnchereRepositoryImpl enchereRepositoryImpl;

    public ArticleVenduServiceImpl(ArticleVenduRepository articleVenduRepository, EnchereRepositoryImpl enchereRepositoryImpl) {
        this.articleVenduRepository = articleVenduRepository;
        this.enchereRepositoryImpl = enchereRepositoryImpl;
    }

    @Override
    public ArticleVendu findArticleById(int id) {
       return articleVenduRepository.findArticleById(id);
    }

    @Override
    public List<ArticleVenduDto> AfficherListeArticleVendu() {

        return articleVenduRepository.listeArticleVendu();
    }

    @Override
    public List<ArticleVenduDto> AfficherlisteArticleVenduByNom(String recherche) {
        return articleVenduRepository.listeArticleVenduByNom(recherche);
    }

    @Override
    public List<ArticleVenduDto> AfficherListeArticleVenduFiltree(int no_categorie, String recherche) {
        return articleVenduRepository.listeArticleFiltree(no_categorie,recherche);
    }

    @Override
    public List<ArticleVenduDto> listeArticleVenduByVendeur(String pseudoVendeur) {
        return articleVenduRepository.listeArticleVenduByVendeur(pseudoVendeur);
    }

    @Override
    public void creerArticle(ArticleVendu articleVendu, Retrait retrait, Utilisateur utilisateur) {
        articleVenduRepository.createArticle(articleVendu, retrait,utilisateur);
    }

    @Override
    @Transactional
    public void cloturerSiDateEchue(ArticleVendu articleVendu) {

        if (articleVendu.isEtatVente()) {
            return; // déjà clôturée
        }

        if (articleVendu.getDateFinEnchere().isAfter(LocalDateTime.now())) {
            return; // pas encore échue
        }

        cloturerVente(articleVendu);
    }

    @Override
    @Transactional
    public void cloturerVente(ArticleVendu articleVendu) {

        // Vérifier que la vente est en cours
        if (articleVendu.isEtatVente()) {
            throw new EnchereTermineeException();
        }

        //récupérer enchereGagnante
        Optional<Enchere> meilleureEnchereOpt = enchereRepositoryImpl.findBestEnchere(articleVendu.getNoArticle());
        if (meilleureEnchereOpt.isPresent()) {
            articleVenduRepository.setGagnant(articleVendu.getNoArticle(), meilleureEnchereOpt.get().getUtilisateur().getNoUtilisateur());

        }


        //modifier etatVente à true
        articleVenduRepository.updateEtatVente(articleVendu.getNoArticle(), true);
    }

    @Override
    public boolean articleEstVendu(int id) {
        return articleVenduRepository.existsByIdAndEtatVenteTrue(id);
    }

    @Override
    public List<ArticleVenduDto> GestionMesVentes(String choixRadio, String[] choixCheckBoxVentes, String [] choixCheckBoxAchats, String vendeur, int no_categorie, String recherche, int acheteurID) {

        List<ArticleVenduDto> listeArticleVendufiltre = new ArrayList<>();

        if (choixRadio.equals("achats")) {
            listeArticleVendufiltre = articleVenduRepository.listeMesAchats(acheteurID,no_categorie,recherche, choixCheckBoxAchats);

        } else if (choixRadio.equals("ventes")) {
            listeArticleVendufiltre = articleVenduRepository.listeMesVentes(vendeur,no_categorie,recherche,choixCheckBoxVentes);
        }

        return listeArticleVendufiltre;
    }

    //TEST LENA
    @Override
    public boolean utilisateurADesVentes(int noUtilisateur) {
        return articleVenduRepository.countArticlesVendusParUtilisateur(noUtilisateur) > 0;
    }
}
