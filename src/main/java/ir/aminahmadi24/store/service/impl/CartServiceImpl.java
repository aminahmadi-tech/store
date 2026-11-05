package ir.aminahmadi24.store.service.impl;

import ir.aminahmadi24.store.config.ConstantMessage;
import ir.aminahmadi24.store.dto.AddProductToCartRequest;
import ir.aminahmadi24.store.dto.CartDto;
import ir.aminahmadi24.store.dto.CartItemDto;
import ir.aminahmadi24.store.entity.CartEntity;
import ir.aminahmadi24.store.entity.CartItemEntity;
import ir.aminahmadi24.store.entity.ProductEntity;
import ir.aminahmadi24.store.entity.UserEntity;
import ir.aminahmadi24.store.enumeration.Message;
import ir.aminahmadi24.store.exception.CartNotFoundException;
import ir.aminahmadi24.store.exception.InsufficientProductStockException;
import ir.aminahmadi24.store.exception.ProductNotFoundException;
import ir.aminahmadi24.store.exception.UserNotFoundException;
import ir.aminahmadi24.store.mapper.CartMapper;
import ir.aminahmadi24.store.repository.CartItemRepository;
import ir.aminahmadi24.store.repository.CartRepository;
import ir.aminahmadi24.store.repository.ProductRepository;
import ir.aminahmadi24.store.repository.UserRepository;
import ir.aminahmadi24.store.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public CartDto addToCart(Long userId, AddProductToCartRequest request) {
        ProductEntity product = findProduct(request.productId());
        validateQuantity(product);

        CartEntity cart = getOrCreateCart(userId);
        addOrIncrementItem(cart, product);

        return buildCartDto(cart.getId());
    }

    @Override
    @Transactional
    public void removeFromCart(Long userId, Long productId) {
        CartEntity cart = findCartByUserId(userId);
        CartItemEntity cartItem = findProductInCart(cart.getId(), productId);

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
            cartItemRepository.deleteById(cartItem.getId());
        }

    }

    private ProductEntity findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(Message.PRODUCT_NOT_FOUND.name()));
    }

    private void validateQuantity(ProductEntity product) {
        int productTotalQuantityInAllCarts =
                cartItemRepository.getProductTotalQuantityInAllCarts(product.getId()).orElse(0);

        if (productTotalQuantityInAllCarts >= product.getQuantity())
            throw new InsufficientProductStockException(Message.INSUFFICIENT_PRODUCT_STOCK.name());
    }

    private CartEntity getOrCreateCart(Long userId) {
        return cartRepository.findByUser_Id(userId)
                .orElseGet(() -> createCart(userId));
    }

    private CartEntity createCart(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ConstantMessage.USER_DOES_NOT_EXIST));

        CartEntity cart = new CartEntity();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    private void addOrIncrementItem(CartEntity cart, ProductEntity product) {
        cartItemRepository.findByCart_IdAndProduct_Id(
                        cart.getId(), product.getId())
                .ifPresentOrElse(
                        cartItem -> {
                            cartItem.setQuantity(cartItem.getQuantity() + 1);
                            cartItemRepository.save(cartItem);
                        },
                        () -> {
                            CartItemEntity cartItem = createCartItem(cart, product);
                            cartItemRepository.save(cartItem);
                        }
                );
    }

    private CartItemEntity createCartItem(CartEntity cart, ProductEntity product) {
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setUnitPrice(product.getPrice());
        return cartItem;
    }

    private CartDto buildCartDto(Long cartId) {
        List<CartItemEntity> cartItemEntities = cartItemRepository.findByCart_Id(cartId);
        List<CartItemDto> cartItemDtos = CartMapper.convertCartItemListToCartItemDtoList(cartItemEntities);
        return CartMapper.getCartDto(cartId, cartItemDtos);
    }

    private CartEntity findCartByUserId(Long userId) {
        return cartRepository.findByUser_Id(userId)
                .orElseThrow(() -> new CartNotFoundException(Message.CART_NOT_FOUND.name()));
    }

    private CartItemEntity findProductInCart(Long cartId, Long productId) {
        return cartItemRepository.findByCart_IdAndProduct_Id(cartId, productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(Message.PRODUCT_DOES_NOT_EXIST_IN_CART.name()));
    }


}
