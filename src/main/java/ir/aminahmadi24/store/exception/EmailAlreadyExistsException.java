package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException(CustomError customError, String message)
    {
        super(customError, message);
    }
}
