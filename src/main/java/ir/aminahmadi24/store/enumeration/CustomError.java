package ir.aminahmadi24.store.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomError {
    BAD_REQUEST(400, "BAD_REQUEST"),
    EMAIL_ALREADY_EXISTS(409, "CONFLICT");

    private int statusCode;
    private String errorCode;
}
