DROP VIEW IF EXISTS gestionEncheresMesVentes
GO
CREATE VIEW gestionEncheresMesVentes
AS
select no_article,nom_article, prix_initial, date_debut_encheres, date_fin_encheres, utilisateurs.pseudo AS vendeur, no_categorie
from ARTICLES_VENDUS
         inner join UTILISATEURS on UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur
GO