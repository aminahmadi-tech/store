package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.CheckoutOrderRequest;
import ir.aminahmadi24.store.dto.OrderDto;

public interface OrderService {
    OrderDto checkout(Long userId, CheckoutOrderRequest request);
}
