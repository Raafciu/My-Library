package control.exception;

public class KeyNotFoundException extends Exception {

    private static final String MESSAGE = "Klucz nie istnieje";

    public KeyNotFoundException() {
        super(MESSAGE);
    }
}
