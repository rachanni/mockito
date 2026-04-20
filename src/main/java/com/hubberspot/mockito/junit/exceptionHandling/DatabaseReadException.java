package com.hubberspot.mockito.junit.exceptionHandling;

public class DatabaseReadException extends RuntimeException {
    public DatabaseReadException(String message) {
        super(message);
    }
}
