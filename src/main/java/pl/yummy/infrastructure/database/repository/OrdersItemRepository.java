package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.OrdersItemDAO;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
@AllArgsConstructor
public class OrdersItemRepository implements OrdersItemDAO {
    @Override
    public List<OrdersItemEntity> findByOrderId(Long ordersId) {
        return null;
    }

    @Override
    public List<OrdersItemEntity> findByMenuItemId(Long menuItemId) {
        return null;
    }

    @Override
    public List<OrdersItemEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return null;
    }

    @Override
    public long countByOrderId(Long ordersId) {
        return 0;
    }

    @Override
    public BigDecimal calculateTotalPriceByOrderId(Long ordersId) {
        return null;
    }
}
