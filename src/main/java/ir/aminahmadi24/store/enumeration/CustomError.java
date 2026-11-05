package ir.aminahmadi24.store.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomError {
    BAD_REQUEST(400, "BAD_REQUEST"),
    USER_NOT_FOUND(404, "USER_NOT_FOUND"),
    EMAIL_ALREADY_EXISTS(409, "CONFLICT"),
    CATEGORY_ALREADY_EXISTS(409, "CONFLICT"),
    CATEGORY_NOT_FOUND(404, "CATEGORY_NOT_FOUND"),
    PRODUCT_NOT_FOUND(404, "PRODUCT_NOT_FOUND"),
    INSUFFICIENT_PRODUCT_STOCK(409, "CONFLICT");

    private int statusCode;
    private String errorCode;
}
