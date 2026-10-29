package ir.aminahmadi24.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank(message = "Name is required.")
        @Size(min = 3, max = 255)
        String name
) {}
