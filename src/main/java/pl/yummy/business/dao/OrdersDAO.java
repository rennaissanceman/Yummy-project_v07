package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.OrdersEntity;

import java.util.List;
import java.util.Optional;

public interface OrdersDAO {

    /* CRUD */
    /* create */
    OrdersEntity createOrders(OrdersEntity order);

    /* read */
    Optional<OrdersEntity> findOrdersById(Long ordersId);

    List<OrdersEntity> findOrdersByCustomerId(Long customerId);

    /* update */
    void updateOrder(OrdersEntity order);


    /* delete */
    void deleteOrder(Long id);

}

