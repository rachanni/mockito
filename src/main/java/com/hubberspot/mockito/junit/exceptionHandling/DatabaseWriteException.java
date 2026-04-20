package com.hubberspot.mockito.junit.exceptionHandling;

public class DatabaseWriteException extends RuntimeException {
    public DatabaseWriteException(String message) {
        super(message);
    }
}
