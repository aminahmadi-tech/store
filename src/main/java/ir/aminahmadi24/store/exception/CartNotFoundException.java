package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;

public class CartNotFoundException extends BusinessException {
    public CartNotFoundException(String message) {
        super(CustomError.CART_NOT_FOUND,message);
    }
}
