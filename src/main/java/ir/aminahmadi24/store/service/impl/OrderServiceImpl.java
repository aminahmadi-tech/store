package ir.aminahmadi24.store.service.impl;

import ir.aminahmadi24.store.dto.CartDto;
import ir.aminahmadi24.store.dto.CartItemDto;
import ir.aminahmadi24.store.dto.CheckoutOrderRequest;
import ir.aminahmadi24.store.dto.OrderDto;
import ir.aminahmadi24.store.entity.*;
import ir.aminahmadi24.store.enumeration.Message;
import ir.aminahmadi24.store.enumeration.OrderStatus;
import ir.aminahmadi24.store.exception.EmptyCartException;
import ir.aminahmadi24.store.exception.OrderNotFoundException;
import ir.aminahmadi24.store.exception.ProductNotFoundException;
import ir.aminahmadi24.store.exception.UserNotFoundException;
import ir.aminahmadi24.store.mapper.OrderMapper;
import ir.aminahmadi24.store.repository.OrderItemRepository;
import ir.aminahmadi24.store.repository.OrderRepository;
import ir.aminahmadi24.store.repository.ProductRepository;
import ir.aminahmadi24.store.repository.UserRepository;
import ir.aminahmadi24.store.service.CartService;
import ir.aminahmadi24.store.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartService cartService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;


    @Override
    @Transactional
    public OrderDto checkout(Long userId, CheckoutOrderRequest request){
        CartDto cart = cartService.getCart(userId);
        if (cart.isEmpty()){
            throw new EmptyCartException(Message.CART_IS_EMPTY.name());
        }
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(Message.USER_NOT_FOUND.name()));

        Set<Long> productIds = cart.getItems().stream()
                .map(item -> item.getProduct().id())
                .collect(Collectors.toSet());

        Map<Long, ProductEntity> products = productRepository.findAllById(productIds).stream()
                .collect(Collectors.toMap(
                        product -> product.getId(),
                        product -> product));

        if (products.size() != productIds.size())
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND.name());

        List<OrderItemEntity> orderItems = new ArrayList<>();
        long totalPrice = 0;
        for (CartItemDto cartItem : cart.getItems()){
            ProductEntity product = products.get(cartItem.getProduct().id());
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(product.getPrice());
            orderItems.add(orderItem);
            totalPrice += product.getPrice() * cartItem.getQuantity();
        }

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setRecipientName(request.getRecipientName());
        order.setRecipientPhone(request.getRecipientPhone());
        order.setShippingAddress(request.getShippingAddress());
        order.setPostalCode(request.getPostalCode());
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        orderItems.forEach(item -> item.setOrder(order));

        orderItems = orderItemRepository.saveAll(orderItems);
        cartService.clearCart(userId);
        return OrderMapper.getOrderDto(order, orderItems);
    }

    @Override
    public List<OrderDto> getAllUserOrders(Long userId) {
        List<OrderEntity> orders = orderRepository.findAllByUser_Id(userId);
        if(orders.isEmpty())
            throw new OrderNotFoundException(Message.USER_DOES_NOT_HAVE_ANY_ORDERS.name());

        List<OrderDto> orderDtos = new ArrayList<>();

        for (OrderEntity order: orders){
            List<OrderItemEntity> orderItems = orderItemRepository.findByOrder_Id(order.getId());
            OrderDto orderDto = OrderMapper.getOrderDto(order, orderItems);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}
