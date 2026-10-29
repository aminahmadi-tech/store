package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message){
        super(CustomError.USER_NOT_FOUND, message);
    }
}
