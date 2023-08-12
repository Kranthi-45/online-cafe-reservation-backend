package com.cafe.exception;
//Global Exceptions instead Writing each exception seperatly in file
public class GlobalExceptions {

    public static class InvalidItemException extends RuntimeException {
        public InvalidItemException(String message) {
            super(message);
        }
    }

    public static class ItemCreationException extends RuntimeException {
        public ItemCreationException(String message) {
            super(message);
        }
    }

    public static class ItemNotFoundException extends RuntimeException {
        public ItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class NoItemsFoundException extends RuntimeException {
        public NoItemsFoundException(String message) {
            super(message);
        }
    }
    
    public class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    // You can add more custom exception classes if needed
}
