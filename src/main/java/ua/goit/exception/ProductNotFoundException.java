package ua.goit.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message, UUID id) {
        super(message);
    }
}
