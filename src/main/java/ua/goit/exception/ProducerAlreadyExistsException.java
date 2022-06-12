package ua.goit.exception;

public class ProducerAlreadyExistsException extends RuntimeException {

    public ProducerAlreadyExistsException(String message) {
        super(message);
    }
}
