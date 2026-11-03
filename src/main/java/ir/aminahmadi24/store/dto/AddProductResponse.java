package ir.aminahmadi24.store.dto;

public record AddProductResponse(
        Long id,
        String name,
        String description,
        Long price,
        Integer quantity,
        Short categoryId) {
}
