package ir.aminahmadi24.store.repository;

import ir.aminahmadi24.store.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
