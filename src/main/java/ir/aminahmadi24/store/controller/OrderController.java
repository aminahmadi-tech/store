package ir.aminahmadi24.store.controller;

import ir.aminahmadi24.store.dto.OrderDto;
import ir.aminahmadi24.store.entity.OrderEntity;
import ir.aminahmadi24.store.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllUserOrders(@AuthenticationPrincipal Long userId){
        return orderService.getAllUserOrders(userId);
    }

}
