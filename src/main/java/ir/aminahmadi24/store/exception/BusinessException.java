package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;
import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
    private final CustomError customError;
    public BusinessException(CustomError customError, String message) {
        super(message);
        this.customError = customError;
    }
}
