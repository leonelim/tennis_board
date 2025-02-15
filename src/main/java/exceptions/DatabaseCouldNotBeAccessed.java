package exceptions;

public class DatabaseCouldNotBeAccessed extends RuntimeException {
    public DatabaseCouldNotBeAccessed(String message) {
        super(message);
    }
}
