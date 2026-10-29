package ir.aminahmadi24.store.dto;

import ir.aminahmadi24.store.enumeration.UserRole;

import java.time.Instant;

public record UserDto (
        Long id, String name, String email, UserRole role, Instant createdAt, Instant updatedAt) {
}
