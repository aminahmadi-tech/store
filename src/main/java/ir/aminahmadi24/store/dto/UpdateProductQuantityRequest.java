package ir.aminahmadi24.store.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateProductQuantityRequest(
        @PositiveOrZero
        @NotNull
        @Max(value = 1000)
        Integer quantity
) {
}
