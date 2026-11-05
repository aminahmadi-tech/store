package ir.aminahmadi24.store.mapper;

import ir.aminahmadi24.store.dto.CartDto;
import ir.aminahmadi24.store.dto.CartItemDto;
import ir.aminahmadi24.store.dto.ProductDtoV2;
import ir.aminahmadi24.store.entity.CartItemEntity;

import java.util.List;

public abstract class CartMapper {

    public static CartItemDto convertCartItemEntityToCartItemDto(CartItemEntity cartItem){
        ProductDtoV2 productDtoV2 = ProductMapper.convertProductEntityToProductDtoV2(cartItem.getProduct());
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCartItemId(cartItem.getId());
        cartItemDto.setCartId(cartItem.getCart().getId());
        cartItemDto.setProduct(productDtoV2);
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setUnitPrice(cartItem.getUnitPrice());
        cartItemDto.setTotalPrice(cartItem.getTotalPrice());
        return cartItemDto;
    }

    public static List<CartItemDto> convertCartItemListToCartItemDtoList(List<CartItemEntity> list){
        return list.stream()
                .map(CartMapper::convertCartItemEntityToCartItemDto)
                .toList();
    }

    public static CartDto getCartDto(Long cartId, List<CartItemDto> cartItemDtos){
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cartId);
        cartDto.setCartItems(cartItemDtos);
        cartDto.setTotalPrice(cartDto.calculateTotalPrice());
        return cartDto;
    }

}
