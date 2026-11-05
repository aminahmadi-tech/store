package ir.aminahmadi24.store.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class CartDto {
    private Long cartId;
    private List<CartItemDto> items;
    private Long totalPrice = 0L;

    public Long calculateTotalPrice(){
        for (CartItemDto cartItemDto: items){
            totalPrice += cartItemDto.getTotalPrice();
        }
        return totalPrice;
    }
}
