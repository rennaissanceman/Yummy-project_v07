package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.CourierEntity;

import java.util.List;
import java.util.Optional;

public interface CourierDAO {
    CourierEntity findById(Long id); // Znajdź kuriera po ID
    List<CourierEntity> findAvailableCouriers(); // Znajdź kurierów dostępnych do dostawy
    void save(CourierEntity courier); // Zapisz lub zaktualizuj kuriera
    void deleteById(Long id); // Usuń kuriera
    List<CourierEntity> findByDeliveryArea(String area); // Znajdź kurierów w danym regionie
}

