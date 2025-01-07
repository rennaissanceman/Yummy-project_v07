package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.OrdersEntity;

import java.util.List;

public interface OrdersDAO {

    // Niestandardowa metoda
    List<OrdersEntity> findByCustomerId(Long customerId);
}
