package fr.enchere.exception;

public abstract class MetierException extends RuntimeException {

    public MetierException(String message) {
        super(message);
    }
}