package ir.aminahmadi24.store.repository;

import ir.aminahmadi24.store.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByNameIgnoreCase(String name);

}
