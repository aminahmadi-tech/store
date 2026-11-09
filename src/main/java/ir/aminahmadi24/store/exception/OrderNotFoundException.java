package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(String message) {

        super(CustomError.ORDER_NOT_FOUND,message);
    }
}
