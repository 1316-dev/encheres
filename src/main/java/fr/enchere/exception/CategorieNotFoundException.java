package fr.enchere.exception;

public class CategorieNotFoundException extends RuntimeException {
    public CategorieNotFoundException(long id) {
        super("Categorie avec le numéro :" + id + "not found");
    }
}
