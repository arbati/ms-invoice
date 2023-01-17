package com.ensa.msinvoice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class MsInvoiceException {

    @ExceptionHandler(DbException.class)
    public ResponseStatusException dbException(DbException exception){

        if(exception instanceof EntityNotFoundException){
            return new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage(),exception);
        }else {
            return new ResponseStatusException(HttpStatus.CONFLICT,exception.getMessage(),exception);
        }

    }


}
