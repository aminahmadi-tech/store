package ir.aminahmadi24.store.dto;

import ir.aminahmadi24.store.entity.OrderItemEntity;

public record OrderItemDto(
    Long productId,
    String productName,
    long unitPrice,
    int quantity,
    long totalPrice
) {

}
