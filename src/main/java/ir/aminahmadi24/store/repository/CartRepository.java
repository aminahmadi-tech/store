package ir.aminahmadi24.store.repository;

import ir.aminahmadi24.store.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    boolean existsByUser_Id(Long userId);
    Optional<CartEntity> findByUser_Id(Long userId);
}
