package ir.aminahmadi24.store.dto;

public record ErrorResponse(
        boolean success,
        int statusCode,
        String errorCode,
        String message) {
}
