package ir.aminahmadi24.store.mapper;

import ir.aminahmadi24.store.dto.OrderDto;
import ir.aminahmadi24.store.dto.OrderItemDto;
import ir.aminahmadi24.store.entity.OrderEntity;
import ir.aminahmadi24.store.entity.OrderItemEntity;

import java.util.List;

public class OrderMapper
{
    public static OrderDto getOrderDto(OrderEntity order, List<OrderItemEntity> items){
        return new OrderDto(
                order.getId(),
                order.getStatus(),
                order.getRecipientName(),
                order.getRecipientPhone(),
                order.getShippingAddress(),
                order.getPostalCode(),
                order.getTotalPrice(),
                items.stream().map(OrderMapper::getOrderItemDto).toList()
        );
    }
    public static OrderItemDto getOrderItemDto(OrderItemEntity item){
        return new OrderItemDto(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getUnitPrice(),
                item.getQuantity(),
                item.getUnitPrice() * item.getQuantity()
        );
    }

}
