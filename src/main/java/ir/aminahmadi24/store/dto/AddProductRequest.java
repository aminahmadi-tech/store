package ir.aminahmadi24.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddProductRequest {
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 255)
    private String name;

    private String description;

    @PositiveOrZero
    @NotNull
    private Long price;

    @PositiveOrZero
    @NotNull
    private Integer quantity;

    @PositiveOrZero
    @NotNull
    private Short categoryId;
}
