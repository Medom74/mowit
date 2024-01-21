package com.mowit.core.exception;

/**
 * Exception thrown for an invalid file format.
 */
public class InvalidFileFormatException extends RuntimeException {
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
