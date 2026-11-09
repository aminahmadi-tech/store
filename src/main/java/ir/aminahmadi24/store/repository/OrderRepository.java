package ir.aminahmadi24.store.repository;

import ir.aminahmadi24.store.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAllByUser_Id(Long userId);
}
