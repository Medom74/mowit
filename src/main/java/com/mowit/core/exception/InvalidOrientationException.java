package com.mowit.core.exception;

/**
 * Exception thrown for an invalid orientation.
 */
public class InvalidOrientationException extends RuntimeException {
    public InvalidOrientationException(String message) {
        super(message);
    }
}