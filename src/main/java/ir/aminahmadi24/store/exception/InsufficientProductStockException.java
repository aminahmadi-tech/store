package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.enumeration.CustomError;
import ir.aminahmadi24.store.enumeration.Message;

public class InsufficientProductStockException extends BusinessException{
    public InsufficientProductStockException(String message){
        super(CustomError.INSUFFICIENT_PRODUCT_STOCK, message);
    }
}
