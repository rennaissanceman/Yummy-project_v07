package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.DeliveryEntity;

import java.util.List;

public interface DeliveryDAO {

    /* CRUD */
    /* create */
    /* read */
    /* update */
    /* delete */

    DeliveryEntity findById(Long id); // Znajdź dostawę po ID
    List<DeliveryEntity> findByCourierId(Long courierId); // Znajdź dostawy obsługiwane przez kuriera
    List<DeliveryEntity> findPendingDeliveries(); // Znajdź oczekujące dostawy
    void save(DeliveryEntity delivery); // Zapisz lub zaktualizuj dostawę
    void deleteById(Long id); // Usuń dostawę
}

