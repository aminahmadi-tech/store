package ir.aminahmadi24.store.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

public class BaseEntity {


    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_at")
    private Instant updatedAt;

    @PrePersist
    void onCreate(){
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate(){
        updatedAt = Instant.now();
    }
}
