package ir.aminahmadi24.store.dto;

import java.time.Instant;

public record CategoryDto(
        Short id, String name, Instant createdAt, Instant updatedAt) {
}
