DROP VIEW IF EXISTS afficherVentesEnCours
GO

CREATE VIEW afficherVentesEnCours
AS
select no_article,nom_article, prix_initial, date_fin_encheres, utilisateurs.pseudo AS vendeur, no_categorie
from ARTICLES_VENDUS
         inner join UTILISATEURS on UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur
    GO

DROP VIEW IF EXISTS gestionEncheresMesVentes
GO

CREATE VIEW gestionEncheresMesVentes
AS
select no_article,nom_article, prix_initial, date_debut_encheres, date_fin_encheres, utilisateurs.pseudo AS vendeur, no_categorie
from ARTICLES_VENDUS
         inner join UTILISATEURS on UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur
GO


/*creer la view pour achat à refaire
DROP VIEW IF EXISTS gestionEncheresMesAchats
GO
CREATE VIEW gestionEncheresMesAchats
AS
select ARTICLES_VENDUS.no_article,nom_article, prix_initial, date_debut_encheres, date_fin_encheres, utilisateurs.pseudo AS vendeur, no_categorie
from ARTICLES_VENDUS
         inner join UTILISATEURS on UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_utilisateur
         left join ENCHERES ON UTILISATEURS.no_utilisateur = ENCHERES.no_utilisateur
WHERE ENCHERES.no_utilisateur = 2
GO*/

