package P1_PROJECT.customExceptions;

public class SpaceMarineNotFoundException extends RuntimeException {
    public SpaceMarineNotFoundException(String message) {
        super(message);
    }
}
