package StandManagerProject.standManager.Exceptions;

public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException() {
        super();
    }

    public BrandNotFoundException(String message) {
        super(message);
    }

    public BrandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BrandNotFoundException(Throwable cause) {
        super(cause);
    }
}