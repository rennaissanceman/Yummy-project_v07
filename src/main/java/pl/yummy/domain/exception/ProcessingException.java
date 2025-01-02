package pl.yummy.domain.exception;

public class ProcessingException extends RuntimeException{

    public ProcessingException (final String message) {
        super(message);
    }
}
