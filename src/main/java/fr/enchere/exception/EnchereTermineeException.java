package fr.enchere.exception;

public class EnchereTermineeException extends MetierException {
    public EnchereTermineeException() {
        super("L'enchère est terminée");
    }
}