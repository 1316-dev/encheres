
/* SUPPRIMER LE CONTENU DES TABLES*/

DELETE FROM RETRAITS;
DELETE FROM ENCHERES;
DELETE FROM ARTICLES_VENDUS;
DELETE FROM CATEGORIES;
DELETE FROM UTILISATEURS;


/* CATEGORIES */
SET IDENTITY_INSERT CATEGORIES ON;

INSERT INTO CATEGORIES (no_categorie, libelle) VALUES
                                                   (1, 'Informatique'),
                                                   (2, 'Ameublement'),
                                                   (3, 'Vêtements'),
                                                   (4, 'Sport'),
                                                   (5, 'Livre');

SET IDENTITY_INSERT CATEGORIES OFF;

/* UTILISATEURS */

    SET IDENTITY_INSERT UTILISATEURS ON;

INSERT INTO UTILISATEURS
(no_utilisateur, pseudo, nom, prenom, email, telephone,
 rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES
    (1, 'jdupont', 'DUPONT', 'Jean', 'jean.dupont@mail.com', '0600000001',
     '10 rue Victor Hugo', '75001', 'Paris', 'pwd123', 100, 0),

    (2, 'mMartin', 'MARTIN', 'Marie', 'marie.martin@mail.com', '0600000002',
     '5 avenue de France', '69000', 'Lyon', 'pwd123', 150, 0),

    (3, 'admin', 'ADMIN', 'Super', 'admin@mail.com', NULL,
     '1 rue Admin', '31000', 'Toulouse', 'adminpwd', 500, 1);

SET IDENTITY_INSERT UTILISATEURS OFF;

    SET IDENTITY_INSERT ARTICLES_VENDUS ON;

/* ARTICLES VENDUS */
INSERT INTO ARTICLES_VENDUS
(no_article, nom_article, description,
 date_debut_encheres, date_fin_encheres,
 prix_initial, prix_vente, no_utilisateur, no_categorie)
VALUES
    (1, 'PC Portable', 'PC gamer Asus 32Go RAM',
     CAST(GETDATE() AS date),
     DATEADD(day, 10, CAST(GETDATE() AS date)),
     500, NULL, 1, 1),

    (2, 'Canapé', 'Canapé 3 places',
     CAST(GETDATE() AS date),
     DATEADD(day, 7, CAST(GETDATE() AS date)),
     200, NULL, 2, 2),

    (3, 'Vélo', 'Vélo de course',
     CAST(GETDATE() AS date),
     DATEADD(day, 9, CAST(GETDATE() AS date)),
     300, NULL, 1, 4);

SET IDENTITY_INSERT ARTICLES_VENDUS OFF;

/*A VOIR POUR AUTOGENERER A PARTIR ADRESSE VENDEUR */
/* RETRAITS */
INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
    (1, '10 rue Victor Hugo', '75001', 'Paris'),
    (2, '5 avenue de France', '69000', 'Lyon'),
    (3, '10 rue Victor Hugo', '75001', 'Paris');


/* Revoir si besoin DATE ET HEURE*/
/* ENCHERES */

SET IDENTITY_INSERT ENCHERES ON;



INSERT INTO ENCHERES
(no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur)
VALUES
    (1, DATEADD(day, 1, GETDATE()), 520, 1, 2),
    (2, DATEADD(day, 1, GETDATE()), 550, 1, 3),
    (3, DATEADD(day, 1, GETDATE()), 220, 2, 1),
    (4, DATEADD(day, 1, GETDATE()), 350, 3, 2);

SET IDENTITY_INSERT ENCHERES OFF;