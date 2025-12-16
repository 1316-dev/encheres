package fr.enchere.exception;

public class UtilisateurNotFound extends RuntimeException {
    public UtilisateurNotFound(String message) {
        super(message);
    }
}
