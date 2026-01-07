package fr.enchere.bll;

import fr.enchere.bo.Utilisateur;
import fr.enchere.exception.EmailDejaUtiliseException;
import fr.enchere.exception.PseudoDejaUtiliseException;
import fr.enchere.exception.UtilisateurNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

    @Transactional
    @SpringBootTest
    public class UtilisateurServiceImplTest {

        @Autowired
        private UtilisateurService utilisateurService;

        // je crée un utilisateur test
        private Utilisateur createUtilisateurTest(String pseudo, String email) {
            Utilisateur user = new Utilisateur();
            user.setPseudo(pseudo);
            user.setNom("Test");
            user.setPrenom("Utilisateur");
            user.setEmail(email);
            user.setTelephone("0600000000");
            user.setRue("1 rue du test");
            user.setCodePostal("75000");
            user.setVille("Paris");
            user.setMotDePasse("password123");
            return user;
        }

        @Test
        @DisplayName("Créer utilisateur nominal")
        public void testCreerUtilisateurCasNominal() {
            // Arrange
            Utilisateur user = createUtilisateurTest("pseudo_bll", "bll@test.com");
            // Act
            utilisateurService.creerUtilisateur(user);
            // Assert
            assertNotNull(user.getNoUtilisateur());
            Utilisateur enBase = utilisateurService.findUserById(user.getNoUtilisateur());
            assertEquals("pseudo_bll", enBase.getPseudo());
            assertEquals("bll@test.com", enBase.getEmail());
        }

        @Test
        @DisplayName("Créer utilisateur avec email déjà utilisé")
        public void testCreerUtilisateurEmailDejaUtilise() {
            // Arrange
            Utilisateur user1 = createUtilisateurTest("pseudo1", "duplicate@test.com");
            utilisateurService.creerUtilisateur(user1);

            Utilisateur user2 = createUtilisateurTest("pseudo2", "duplicate@test.com");
            // Act & Assert
            assertThrows(EmailDejaUtiliseException.class, () -> utilisateurService.creerUtilisateur(user2));
        }

        @Test
        @DisplayName("Créer utilisateur avec pseudo déjà utilisé")
        public void testCreerUtilisateurPseudoDejaUtilise() {
            // Arrange
            Utilisateur user1 = createUtilisateurTest("duplicate_pseudo", "email1@test.com");
            utilisateurService.creerUtilisateur(user1);

            Utilisateur user2 = createUtilisateurTest("duplicate_pseudo", "email2@test.com");
            // Act & Assert
            assertThrows(PseudoDejaUtiliseException.class, () -> utilisateurService.creerUtilisateur(user2));
        }

        @Test
        @DisplayName("Find utilisateur par pseudo")
        public void testFindUserByUsername() {
            Utilisateur user = createUtilisateurTest("pseudo_find", "find@test.com");
            utilisateurService.creerUtilisateur(user);

            Utilisateur enBase = utilisateurService.findUserByUsername("pseudo_find");
            assertNotNull(enBase);
            assertEquals("pseudo_find", enBase.getPseudo());
        }

        @Test
        @DisplayName("Supprimer utilisateur existant")
        public void testSupprimerUtilisateur() {
            Utilisateur user = createUtilisateurTest("pseudo_delete", "delete@test.com");
            utilisateurService.creerUtilisateur(user);
            int id = user.getNoUtilisateur();

            utilisateurService.supprimerUtilisateur(id);

            assertThrows(UtilisateurNotFound.class, () -> utilisateurService.findUserById(id));
        }

        @Test
        @DisplayName("existsByPseudo et existsByEmail")
        public void testExistsMethods() {
            Utilisateur user = createUtilisateurTest("pseudo_exists", "exists@test.com");
            utilisateurService.creerUtilisateur(user);

            assertTrue(utilisateurService.existsByPseudo("pseudo_exists"));
            assertTrue(utilisateurService.existsByEmail("exists@test.com"));
            assertFalse(utilisateurService.existsByPseudo("inexistant"));
            assertFalse(utilisateurService.existsByEmail("inexistant@test.com"));
        }
    }


