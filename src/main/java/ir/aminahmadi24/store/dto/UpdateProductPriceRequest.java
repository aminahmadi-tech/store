package ir.aminahmadi24.store.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateProductPriceRequest(
        @PositiveOrZero
        @NotNull
        Long price
) {
}
