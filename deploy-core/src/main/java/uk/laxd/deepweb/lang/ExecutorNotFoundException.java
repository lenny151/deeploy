package uk.laxd.deepweb.lang;

/**
 * Created by lawrence on 24/02/16.
 */
public class ExecutorNotFoundException extends RuntimeException {
    public ExecutorNotFoundException() {
    }

    public ExecutorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutorNotFoundException(Throwable cause) {
        super(cause);
    }

    public ExecutorNotFoundException(String type) {
        super("Couldn't find executor with type = '" + type +"'");
    }
}
