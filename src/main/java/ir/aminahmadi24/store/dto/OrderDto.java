package ir.aminahmadi24.store.dto;

import ir.aminahmadi24.store.entity.OrderEntity;
import ir.aminahmadi24.store.entity.OrderItemEntity;
import ir.aminahmadi24.store.enumeration.OrderStatus;
import ir.aminahmadi24.store.mapper.OrderMapper;

import java.util.List;

public record OrderDto(
        Long orderId,
        OrderStatus status,
        String recipientName,
        String recipientPhone,
        String shippingAddress,
        String postalCode,
        Long totalPrice,
        List<OrderItemDto> items
) {

}
