package com.ensa.msinvoice.exceptions;

public class EntityNotFoundException extends DbException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
