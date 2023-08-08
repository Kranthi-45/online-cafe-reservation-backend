package com.cafe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Global Exception Handler Instead of creation Each Exception Handler seperately
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidItemException.class, ItemCreationException.class,
                       ItemNotFoundException.class, NoItemsFoundException.class})
    public ResponseEntity<ErrorResponse> handleCustomExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        HttpStatus status = determineHttpStatus(ex);
        return ResponseEntity.status(status).body(errorResponse);
    }

    // Define additional methods for other exception types if needed
    private HttpStatus determineHttpStatus(Exception ex) {
        if (ex instanceof InvalidItemException || ex instanceof ItemCreationException) {
            return HttpStatus.BAD_REQUEST;
        } else if (ex instanceof ItemNotFoundException || ex instanceof NoItemsFoundException) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
