package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;

public class EmptyCartException extends BusinessException {
    public EmptyCartException(String message) {

      super( CustomError.CART_IS_EMPTY,message);
    }
}
