package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.CheckoutOrderRequest;
import ir.aminahmadi24.store.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto checkout(Long userId, CheckoutOrderRequest request);
    List<OrderDto> getAllUserOrders(Long userId);
}
