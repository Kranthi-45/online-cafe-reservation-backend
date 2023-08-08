package com.cafe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ItemNotFoundExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        		
//        // Get the custom message from the exception
//        String customMessage = ex.getCustomMessage();
//        // Create the ErrorResponse object with the custom message
//        ErrorResponse errorResponse = new ErrorResponse(customMessage);
//        // Return the ResponseEntity with NOT_FOUND status and the error response
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
