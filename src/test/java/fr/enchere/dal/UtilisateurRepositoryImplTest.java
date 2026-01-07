package fr.enchere.dal;

import fr.enchere.bo.Utilisateur;
import fr.enchere.exception.UtilisateurNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class UtilisateurRepositoryImplTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Méthode utilitaire pour créer un utilisateur test
    private Utilisateur createUtilisateurTest(String pseudo, String email) {
        Utilisateur u = new Utilisateur();
        u.setPseudo(pseudo);
        u.setNom("Test");
        u.setPrenom("Utilisateur");
        u.setEmail(email);
        u.setTelephone("0600000000");
        u.setRue("1 rue du test");
        u.setCodePostal("75000");
        u.setVille("Paris");
        u.setMotDePasse("password123");
        return u;
    }

    @Test
    @DisplayName("test findUserById l'id existe")
    public void testFindUserByIdExiste(){
        //AAA
        //Arrange: préparation du test
        int id=3;
        //Act: Appel à la méthode à tester
        Utilisateur utilisateur = utilisateurRepository.findUserById(id);
        //Assert: Vérification du résultat du Act
        assertNotNull(utilisateur);
        assertEquals(id,utilisateur.getNoUtilisateur());
    }

    @Test
    @DisplayName("test findUserById l'id n'existe pas")
    public void testFindUserByIdExistePas(){
        //Arrange
        int id=999;
        //Act et Assert
        assertThrows(UtilisateurNotFound.class,()->utilisateurRepository.findUserById(id));
    }

    @Test
    @DisplayName("test findUserByUsername le pseudo existe")
    public void testFindUserByUsernameCasNominal() {
        //Arrange
        String pseudo = "admin";
        //Act
        Utilisateur utilisateur = utilisateurRepository.findUserByUsername(pseudo);
        //Assert
        assertNotNull(utilisateur);
        assertEquals(pseudo, utilisateur.getPseudo());
    }

    @Test
    @DisplayName("test findUserByUsername le pseudo exite pas")
    public void testFindUserByUsernameExistePas(){
        //Arrange
        String pseudo= "inconnu";
        //Act et Assert
        assertThrows(UtilisateurNotFound.class,() ->utilisateurRepository.findUserByUsername(pseudo));
    }

    @Test
    @DisplayName("test findUserByUsernameOrEmail trouvé par pseudo")
    public void testFindUserByUsernameOrEmailByPseudo() {
        //Arrange
        String pseudo = "admin";
        String mail = "mail_inexistant@test.com";
        //Act
        Utilisateur utilisateur = utilisateurRepository.findUserByUsernameOrEmail(mail, pseudo);
        //Assert
        assertNotNull(utilisateur);
        assertEquals(pseudo, utilisateur.getPseudo());
    }

    @Test
    @DisplayName("test findUserByUsernameOrEmail trouvé par email")
    public void testFindUserByUsernameOrEmailByEmail() {
        //Arrange
        String pseudo = "pseudo_inexistant";
        String mail = "admin@mail.com";
        //Act
        Utilisateur utilisateur = utilisateurRepository.findUserByUsernameOrEmail(mail, pseudo);
        //Assert
        assertNotNull(utilisateur);
        assertEquals(mail, utilisateur.getEmail());
    }

    @Test
    @DisplayName("test findUserByUsernameOrEmail aucun résultat")
    public void testFindUserByUsernameOrEmailExistePas() {
        //Arrange
        String pseudo = "inconnu";
        String mail = "inconnu@test.com";
        //Act et Assert
        assertThrows(UtilisateurNotFound.class, () -> utilisateurRepository.findUserByUsernameOrEmail(mail, pseudo));
    }

    @Test
    @DisplayName("test saveUtilisateur insère un utilisateur et génère un id")
    public void testSaveUtilisateurCasNominal() {
        //Arrange
        Utilisateur utilisateur = createUtilisateurTest("pseudo_test", "test.utilisateur@test.com");
        //Act
        utilisateurRepository.saveUtilisateur(utilisateur);
        //Assert
        assertNotNull(utilisateur.getNoUtilisateur());
        assertTrue(utilisateur.getNoUtilisateur() > 0);
        Utilisateur utilisateurEnBase = utilisateurRepository.findUserById(utilisateur.getNoUtilisateur());
        assertEquals("pseudo_test", utilisateurEnBase.getPseudo());
        assertEquals("test.utilisateur@test.com", utilisateurEnBase.getEmail());
    }

    @Test
    @DisplayName("test deleteUtilisateur supprime un utilisateur existant")
    public void testDeleteUtilisateurCasNominal() {
        //Arrange
        Utilisateur utilisateur = createUtilisateurTest("delete_test", "delete.test@test.com");
        utilisateurRepository.saveUtilisateur(utilisateur);
        int noUtilisateur = utilisateur.getNoUtilisateur();
        //Act
        utilisateurRepository.deleteUtilisateur(noUtilisateur);
        //Assert
        assertThrows(UtilisateurNotFound.class, () -> utilisateurRepository.findUserById(noUtilisateur));
    }

    @Test
    @DisplayName("test existsByEmail retourne true si l'email existe")
    public void testExistsByEmailVrai() {
        //Arrange
        Utilisateur utilisateur = createUtilisateurTest("email_test", "email.test@test.com");
        utilisateurRepository.saveUtilisateur(utilisateur);
        //Act
        boolean exists = utilisateurRepository.existsByEmail("email.test@test.com");
        //Assert
        assertTrue(exists);
    }

    @Test
    @DisplayName("test existsByEmail retourne false si l'email n'existe pas")
    public void testExistsByEmailFaux() {
        //Arrange
        String emailInexistant = "inexistant@test.com";
        //Act
        boolean exists = utilisateurRepository.existsByEmail(emailInexistant);
        //Assert
        assertFalse(exists);
    }

    @Test
    @DisplayName("test existsByPseudo retourne true si le pseudo existe")
    public void testExistsByPseudoVrai() {
        //Arrange
        Utilisateur utilisateur = createUtilisateurTest("pseudo_test_exist", "pseudo_test_exist@test.com");
        utilisateurRepository.saveUtilisateur(utilisateur);
        //Act
        boolean exists = utilisateurRepository.existsByPseudo("pseudo_test_exist");
        //Assert
        assertTrue(exists);
    }

    @Test
    @DisplayName("test existsByPseudo retourne false si le pseudo n'existe pas")
    public void testExistsByPseudoFaux() {
        //Arrange
        String pseudoInexistant = "pseudo_inexistant";
        //Act
        boolean exists = utilisateurRepository.existsByPseudo(pseudoInexistant);
        //Assert
        assertFalse(exists);
    }

}
