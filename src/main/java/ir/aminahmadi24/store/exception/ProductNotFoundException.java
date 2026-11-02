package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(String message){
        super(CustomError.PRODUCT_NOT_FOUND, message);
    }
}
