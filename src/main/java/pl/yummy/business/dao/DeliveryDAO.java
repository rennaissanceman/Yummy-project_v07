package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.DeliveryEntity;

import java.util.List;
import java.util.Optional;

public interface DeliveryDAO {

    /* CRUD */
    /* create */
    DeliveryEntity createDelivery(DeliveryEntity delivery);

    /* read */
    Optional<DeliveryEntity> findDeliveryById(Long deliveryId);
    Optional<DeliveryEntity> findDeliveryByOrderId(Long orderId);
    Optional<DeliveryEntity> findDeliveryByCourierId(Long courierId);
    List<DeliveryEntity> findDeliveriesByStatus(List<String> deliveryStatus);

    /* update */
    DeliveryEntity updateDelivery(DeliveryEntity delivery);

    /* delete */
    void deleteDelivery(Long deliveryId);
}

