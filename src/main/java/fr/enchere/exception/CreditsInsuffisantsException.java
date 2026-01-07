package fr.enchere.exception;

public class CreditsInsuffisantsException extends MetierException {
    private final int noArticle;

    // Constructeur avec noArticle et message
    public CreditsInsuffisantsException(int noArticle, String message) {
        super(message);
        this.noArticle= noArticle;
    }

    public int getArticleId() {
        return noArticle;
    }
}