CREATE VIEW afficherVentesEnCours
AS
select no_article,nom_article, prix_initial, date_fin_encheres, utilisateurs.pseudo AS Vendeur
from ARTICLES_VENDUS
inner join UTILISATEURS on UTILISATEURS.no_utilisateur = UTILISATEURS.no_utilisateur
GO