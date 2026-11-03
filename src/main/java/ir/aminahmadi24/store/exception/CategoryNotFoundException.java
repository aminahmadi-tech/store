package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;

public class CategoryNotFoundException extends BusinessException {
    public CategoryNotFoundException(String message)
    {
        super(CustomError.CATEGORY_NOT_FOUND ,message);
    }
}
