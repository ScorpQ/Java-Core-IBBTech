package P1_PROJECT.customExceptions;

public class GuardNotFoundException extends RuntimeException {
    public GuardNotFoundException(String message) {
        super(message);
    }
}
