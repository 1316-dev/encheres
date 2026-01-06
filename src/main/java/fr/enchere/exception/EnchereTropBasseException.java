package fr.enchere.exception;

import fr.enchere.exception.MetierException;

public class EnchereTropBasseException extends MetierException {
    public EnchereTropBasseException() {
        super("Le montant de l'enchère doit être supérieur au prix actuel");
    }
}