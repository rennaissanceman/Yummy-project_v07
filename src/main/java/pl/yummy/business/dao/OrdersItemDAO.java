package pl.yummy.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;

import java.math.BigDecimal;
import java.util.List;

public interface OrdersItemDAO {

    List<OrdersItemEntity> findByOrderId(Long ordersId);
    List<OrdersItemEntity> findByMenuItemId(Long menuItemId);

    @Query("SELECT o FROM OrdersItemEntity o WHERE o.price BETWEEN :minPrice AND :maxPrice")
    List<OrdersItemEntity> findByPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    long countByOrderId(Long ordersId);

    @Query("SELECT SUM(o.price) FROM OrdersItemEntity o WHERE o.order.id = :ordersId")
    BigDecimal calculateTotalPriceByOrderId(@Param("ordersId") Long ordersId);
}
