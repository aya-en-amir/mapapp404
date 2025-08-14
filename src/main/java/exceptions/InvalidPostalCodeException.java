package exceptions;

public class InvalidPostalCodeException extends Exception {
    public InvalidPostalCodeException(String message) {
        super(message);
    }
}
