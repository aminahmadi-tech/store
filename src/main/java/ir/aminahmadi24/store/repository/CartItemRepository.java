package ir.aminahmadi24.store.repository;

import ir.aminahmadi24.store.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    Optional<CartItemEntity> findByCart_IdAndProduct_Id(Long cartId, Long productId);
    List<CartItemEntity> findByCart_Id(Long cartId);

    @Query(value = """
            SELECT SUM(ci.quantity)
            FROM cart_items ci
            WHERE ci.product_id = :productId
            """, nativeQuery = true)
    Optional<Integer> getProductTotalQuantityInAllCarts(@Param("productId") Long productId);
}
