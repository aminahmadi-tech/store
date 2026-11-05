package ir.aminahmadi24.store.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemDto {
    private Long cartItemId;
    private Long cartId;
    private ProductDtoV2 product;
    private Integer quantity;
    private Long unitPrice;
    private Long totalPrice = 0L;
}
