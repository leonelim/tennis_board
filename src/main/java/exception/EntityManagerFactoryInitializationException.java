package exception;

public class EntityManagerFactoryInitializationException extends RuntimeException {
    public EntityManagerFactoryInitializationException(String message) {
        super(message);
    }
}
