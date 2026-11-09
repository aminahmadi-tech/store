package ir.aminahmadi24.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CheckoutOrderRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String recipientName;

    @NotBlank
    @Size(min = 11, max = 11)
    @Pattern(regexp = "^0[0-9]{10}$")
    private String recipientPhone;

    @NotBlank
    private String shippingAddress;

    @NotBlank
    @Size(min = 10, max = 10)
    private String postalCode;
}
