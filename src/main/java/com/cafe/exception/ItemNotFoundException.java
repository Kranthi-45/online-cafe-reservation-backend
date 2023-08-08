package com.cafe.exception;

public class ItemNotFoundException extends RuntimeException {
//    private String customMessage;

    public ItemNotFoundException(String message) {
        super(message);
    }
    
//    public ItemNotFoundException(String message, String customMessage) {
//        super(message);
//        this.customMessage = customMessage;
//    }
    
}
