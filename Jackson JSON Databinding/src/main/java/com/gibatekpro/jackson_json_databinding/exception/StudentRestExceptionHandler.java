package com.gibatekpro.jackson_json_databinding.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    //Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException studentNotFoundException) {

        //Create a StudentErrorResponse

        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(studentNotFoundException.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        //return response entity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    //Add a general exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleAllException(Exception e) {

        //Create a StudentErrorResponse

        StudentErrorResponse errorResponse = new StudentErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        //return response entity
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
