package ua.goit.exception;

import java.util.UUID;

public class ProducerNotFoundException extends RuntimeException {

    public ProducerNotFoundException(String message, UUID id) {
        super(message);
    }
}
