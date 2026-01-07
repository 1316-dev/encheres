
/* SUPPRIMER LE CONTENU DES TABLES*/

DELETE FROM RETRAITS;
DELETE FROM ENCHERES;
DELETE FROM ARTICLES_VENDUS;
DELETE FROM CATEGORIES;
DELETE FROM UTILISATEURS;


/* CATEGORIES */
SET IDENTITY_INSERT CATEGORIES ON;

INSERT INTO CATEGORIES (no_categorie, libelle) VALUES
                                                   (1, 'Toutes'),
                                                   (2, 'Informatique'),
                                                   (3, 'Ameublement'),
                                                   (4, 'Vêtements'),
                                                   (5, 'Sport'),
                                                   (6, 'Livre');

SET IDENTITY_INSERT CATEGORIES OFF;

/* UTILISATEURS */

    SET IDENTITY_INSERT UTILISATEURS ON;

INSERT INTO UTILISATEURS
(no_utilisateur, pseudo, nom, prenom, email, telephone,
 rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES
    (1, 'jdupont', 'DUPONT', 'Jean', 'jean.dupont@mail.com', '0600000001',
     '10 rue Victor Hugo', '75001', 'Paris', '{bcrypt}$2a$10$OyG5YbdLxhxIQyrQFil31uf6HLcCehaMIkXGmSWbCxff5dSvQ.rQq', 100, 0),

    (2, 'mMartin', 'MARTIN', 'Marie', 'marie.martin@mail.com', '0600000002',
     '5 avenue de France', '69000', 'Lyon', '{bcrypt}$2a$10$OyG5YbdLxhxIQyrQFil31uf6HLcCehaMIkXGmSWbCxff5dSvQ.rQq', 2000, 0),

    (3, 'admin', 'ADMIN', 'Super', 'admin@mail.com', NULL,
     '1 rue Admin', '31000', 'Toulouse', '{bcrypt}$2a$10$OyG5YbdLxhxIQyrQFil31uf6HLcCehaMIkXGmSWbCxff5dSvQ.rQq', 1000, 1);

SET IDENTITY_INSERT UTILISATEURS OFF;

    SET IDENTITY_INSERT ARTICLES_VENDUS ON;

/* ARTICLES VENDUS */
INSERT INTO ARTICLES_VENDUS
(no_article, nom_article, description,
 date_debut_encheres, date_fin_encheres,
 prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente)
VALUES



    (1, 'PC Portable', 'PC gamer Asus 32Go RAM',
     DATETIMEFROMPARTS(2026, 01, 18, 20, 00, 0, 0), -- 18/12/2025 à 20h00
     DATETIMEFROMPARTS(2026, 01, 28, 20, 00, 0, 0), -- 28/12/2025 à 20h00
     500, 550, 1, 2, 0),

    (2, 'Canapé', 'Canapé 3 places',
     GETDATE(), -- Date et heure actuelle de début pour le test revoir pour fin
     DATEADD(day, 10, GETDATE()), -- Finit dans exactement 10 jours à la même heure
     500, NULL, 2, 3),

    (3, 'Vélo', 'Vélo de course',
     DATETIMEFROMPARTS(2026, 01, 18, 20, 00, 0, 0), -- 18/12/2025 à 20h00
     DATETIMEFROMPARTS(2026, 01, 28, 20, 00, 0, 0), -- 28/12/2025 à 20h00
     500, NULL, 3, 5)
     500, NULL, 1, 5, 0),

    (4, 'Moto', 'Moto de course',
     DATETIMEFROMPARTS(2026, 01, 18, 20, 00, 0, 0), -- 18/12/2025 à 20h00
     DATETIMEFROMPARTS(2026, 01, 28, 20, 00, 0, 0), -- 28/12/2025 à 20h00
     500, NULL, 1, 5, 1)

SET IDENTITY_INSERT ARTICLES_VENDUS OFF;

/*A VOIR POUR AUTOGENERER A PARTIR ADRESSE VENDEUR */
/* RETRAITS */
INSERT INTO RETRAITS (no_article, rue, code_postal, ville, no_utilisateur)
VALUES
    (1, '10 rue Victor Hugo', '75001', 'Paris',1),
    (2, '5 avenue de France', '69000', 'Lyon',2),
    (3, '10 rue Victor Hugo', '75001', 'Paris',3);


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