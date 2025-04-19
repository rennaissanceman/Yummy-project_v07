package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrdersItemJpaRepository extends JpaRepository<OrdersItemEntity, Long> {

    List<OrdersItemEntity> findByMenuItem_MenuItemId(Long menuItemId);


    @Query("SELECT o FROM OrdersItemEntity o WHERE o.unitPrice BETWEEN :minPrice AND :maxPrice")
    List<OrdersItemEntity> findByUnitPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);


    List<OrdersItemEntity> findByOrders_OrdersId(Long ordersId);

    @Query("""
        SELECT SUM(item.unitPrice * item.quantity)
        FROM OrdersItemEntity item
        WHERE item.orders.ordersId = :ordersId
    """)
    BigDecimal calculateTotalPriceByOrderId(@Param("ordersId") Long ordersId);

    long countByOrders_OrdersId(Long ordersId);

}
