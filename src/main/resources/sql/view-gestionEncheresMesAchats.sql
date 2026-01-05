DROP VIEW IF EXISTS gestionEncheresMesAchats
    GO
CREATE VIEW gestionEncheresMesAchats
AS
select distinct ARTICLES_VENDUS.no_article,nom_article, prix_initial, date_debut_encheres, date_fin_encheres, utilisateurs.pseudo AS vendeur, no_categorie, ENCHERES.no_utilisateur As acheteur
from ARTICLES_VENDUS
         inner join ENCHERES ON ARTICLES_VENDUS.no_article = ENCHERES.no_article
         LEFT join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur
    GO