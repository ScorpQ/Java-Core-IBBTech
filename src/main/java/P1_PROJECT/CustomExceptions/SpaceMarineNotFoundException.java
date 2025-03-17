package P1_PROJECT.CustomExceptions;

public class SpaceMarineNotFoundException extends RuntimeException {
    public SpaceMarineNotFoundException(String message) {
        super(message);
    }
}
