package fr.enchere.exception;

public class CreditsInsuffisantsException extends MetierException {
    public CreditsInsuffisantsException() {
        super("Crédits insuffisants pour placer l'enchère");
    }
}