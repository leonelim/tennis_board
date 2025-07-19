package exception;

public class PersistenceFailedException extends RuntimeException {
    public PersistenceFailedException(String message) {
        super(message);
    }
}
