package com.gibatekpro.customer_tracker_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    //Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException customerNotFoundException) {

        //Create a CustomerErrorResponse

        CustomerErrorResponse errorResponse = new CustomerErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(customerNotFoundException.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        //return response entity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    //Add a general exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleAllException(Exception e) {

        //Create a CustomerErrorResponse

        CustomerErrorResponse errorResponse = new CustomerErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        //return response entity
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
