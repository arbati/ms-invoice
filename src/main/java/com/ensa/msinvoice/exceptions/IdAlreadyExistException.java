package com.ensa.msinvoice.exceptions;

public class IdAlreadyExistException extends  DbException{

    public IdAlreadyExistException(String message) {
        super(message);
    }

}
