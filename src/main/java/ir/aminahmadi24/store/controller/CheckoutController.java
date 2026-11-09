package ir.aminahmadi24.store.controller;

import ir.aminahmadi24.store.dto.CheckoutOrderRequest;
import ir.aminahmadi24.store.dto.OrderDto;
import ir.aminahmadi24.store.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final OrderServiceImpl orderService;

    @GetMapping()
    public ResponseEntity<OrderDto> checkout(
            @AuthenticationPrincipal Long userId,
            @Valid @RequestBody CheckoutOrderRequest request){
        OrderDto orderDto = orderService.checkout(userId, request);
        URI location = URI.create("/api/v1/order/" + orderDto.orderId());
        return ResponseEntity.created(location).body(orderDto);
    }
}
