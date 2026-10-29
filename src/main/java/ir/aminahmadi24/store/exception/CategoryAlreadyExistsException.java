package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;

public class CategoryAlreadyExistsException extends BusinessException {
    public CategoryAlreadyExistsException(String message) {

        super(CustomError.CATEGORY_ALREADY_EXISTS, message);
    }
}
