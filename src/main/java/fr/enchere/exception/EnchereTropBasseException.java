package fr.enchere.exception;

public class EnchereTropBasseException extends MetierException {
  /*  public EnchereTropBasseException() {
        super("Le montant de l'enchère doit être supérieur au prix actuel");
    }*/
  private int noArticle;

    // Constructeur avec noArticle et message
    public EnchereTropBasseException(int noArticle, String message) {
        super(message);
        this.noArticle= noArticle;
    }

    public int getArticleId() {
        return noArticle;
    }
}