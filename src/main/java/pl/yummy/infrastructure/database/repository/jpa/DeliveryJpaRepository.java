package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatusEnumEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryJpaRepository extends JpaRepository<DeliveryEntity, Long> {

    // Find all deliveries by a specific courier ID
    List<DeliveryEntity> findByCourier_CourierId(Long courierId);

    Optional<DeliveryEntity> findByOrders_OrdersId(Long ordersId);

    // Find all deliveries by their status
    List<DeliveryEntity> findByDeliveryStatus(DeliveryStatusEnumDomain deliveryStatus);

    // Find deliveries scheduled to start after a specific date
    List<DeliveryEntity> findByStarTimeAfter(OffsetDateTime startTime);

    // Find deliveries that are late (actual delivery time after estimated delivery time)
    List<DeliveryEntity> findByActualDeliveryDateTimeAfterAndEstimatedDeliveryTimeIsNotNull(OffsetDateTime actualDeliveryDateTime);

    // Find deliveries within a specific delivery area
    List<DeliveryEntity> findByAvailableDeliveryArea_AvailableDeliveryAreaId(Long deliveryAreaId);


    @Query("""
        SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END 
        FROM DeliveryEntity d 
        WHERE d.orders.ordersId = :ordersId
    """)
    boolean existsDeliveryForOrder(@Param("ordersId") Long ordersId);
}
