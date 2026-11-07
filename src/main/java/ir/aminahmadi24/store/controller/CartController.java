package ir.aminahmadi24.store.controller;

import ir.aminahmadi24.store.dto.AddProductToCartRequest;
import ir.aminahmadi24.store.dto.CartDto;
import ir.aminahmadi24.store.service.impl.CartServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;

    @PostMapping("/items")
    public ResponseEntity<CartDto> addToCart(
            @AuthenticationPrincipal Long userId,
            @Valid @RequestBody AddProductToCartRequest request
    ){
        return ResponseEntity.ok(cartService.addToCart(userId, request));
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long productId
    ){
        cartService.removeFromCart(userId, productId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<CartDto> getCart(@AuthenticationPrincipal Long userId){
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @DeleteMapping("/items")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal Long userId){
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
