package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.OrdersItemEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrdersItemDAO {

        /* CRUD */
        /* create */
        OrdersItemEntity createOrdersItem(OrdersItemEntity ordersItem);

        /* read */
        Optional<OrdersItemEntity> findOrdersItemById(Long ordersItemId);
        List<OrdersItemEntity> findAllOrdersItems();
        List<OrdersItemEntity> findOrdersItemsByOrderId(Long ordersId);
        List<OrdersItemEntity> findOrdersItemsByMenuItemId(Long menuItemId);
        List<OrdersItemEntity> findOrdersItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

        /* update */
        OrdersItemEntity updateOrdersItem(OrdersItemEntity ordersItem);

        /* delete */
        void deleteOrdersItem(Long ordersItemId);


        long countOrdersItemsByOrderId(Long ordersId);
        BigDecimal calculateTotalPriceByOrderId(Long ordersId);
}
