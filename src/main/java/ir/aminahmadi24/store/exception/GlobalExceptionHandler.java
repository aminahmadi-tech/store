package ir.aminahmadi24.store.exception;

import ir.aminahmadi24.store.dto.ErrorResponse;
import ir.aminahmadi24.store.enumeration.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex){
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String message;
        if(fieldError != null)
            message = fieldError.getField() + ": " + fieldError.getDefaultMessage();
        else
            message = "Request validation failed.";

        ErrorResponse errorResponse = new ErrorResponse(false,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.toString(),
                message);
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                false,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.toString(),
                "Request body is invalid."
        );
        return ResponseEntity.ok(errorResponse);
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                false,
                ex.getCustomError().getStatusCode(),
                ex.getCustomError().getErrorCode(),
                ex.getMessage()
        );
        return ResponseEntity.ok(errorResponse);
    }
}
