package ir.aminahmadi24.store.service;

import ir.aminahmadi24.store.dto.AddProductToCartRequest;
import ir.aminahmadi24.store.dto.CartDto;

public interface CartService {
    CartDto addToCart(Long userId, AddProductToCartRequest request);
}
