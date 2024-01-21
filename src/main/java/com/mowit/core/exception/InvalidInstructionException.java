package com.mowit.core.exception;

/**
 * Exception thrown for an invalid instruction.
 */
public class InvalidInstructionException extends RuntimeException {
    public InvalidInstructionException(String message) {
        super(message);
    }
}
