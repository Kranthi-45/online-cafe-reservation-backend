package com.cafe.exception;

public class NoItemsFoundException extends RuntimeException {
    public NoItemsFoundException(String message) {
        super(message);
    }
}
