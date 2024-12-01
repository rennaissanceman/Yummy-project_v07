package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.OrdersEntity;

import java.util.List;

public interface OrdersDAO {
    OrdersEntity findById(Long id); // Znajdź zamówienie po ID
    List<OrdersEntity> findByCustomerId(Long customerId); // Znajdź zamówienia klienta
    List<OrdersEntity> findByStatus(String status); // Znajdź zamówienia po statusie
    void save(OrdersEntity order); // Zapisz lub zaktualizuj zamówienie
    void deleteById(Long id); // Usuń zamówienie
}

