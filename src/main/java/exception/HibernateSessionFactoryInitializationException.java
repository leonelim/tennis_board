package exception;

public class HibernateSessionFactoryInitializationException extends RuntimeException {
    public HibernateSessionFactoryInitializationException(String message) {
        super(message);
    }
}
