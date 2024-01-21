package com.mowit.core.exception;

/**
 * Exception thrown for an invalid position.
 */
public class InvalidPositionException extends RuntimeException {
    public InvalidPositionException(String message) {
        super(message);
    }
}
