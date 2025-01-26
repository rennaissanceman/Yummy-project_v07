package pl.yummy.business.dao;

import pl.yummy.domain.Courier;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.Orders;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.List;

public interface DeliveryDAO {

    // Find all deliveries by a specific courier ID
    List<Delivery> findByCourier_CourierId(Long courierId);

    // Find all deliveries by their status
    List<Delivery> findByDeliveryStatus(DeliveryStatusEnumDomain deliveryStatus);

    // Find deliveries scheduled to start after a specific date
    List<Delivery> findByStarTimeAfter(OffsetDateTime startTime);

    // Find deliveries that are late (actual delivery time after estimated delivery time)
    List<Delivery> findByActualDeliveryDateTimeAfterAndEstimatedDeliveryTimeIsNotNull(OffsetDateTime actualDeliveryDateTime);

    // Find deliveries within a specific delivery area
    List<Delivery> findByAvailableDeliveryArea_AvailableDeliveryAreaId(Integer deliveryAreaId);





    List<Delivery> findByEstimatedDeliveryTimeBeforeAndDeliveryStatus(OffsetDateTime time, DeliveryStatusEnumDomain status);



    List<Delivery> findLateDeliveries();


    List<Delivery> findByCourier(Courier courier);
    List<Delivery> findByOrders(Orders orders);




}
