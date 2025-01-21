package pl.yummy.business.dao;

import pl.yummy.domain.Courier;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.Orders;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.List;

public interface DeliveryDAO {

    List<Delivery> findByDeliveryStatus(DeliveryStatusEnumDomain status);
    List<Delivery> findByCourierId(Integer courierId);
    List<Delivery> findByEstimatedDeliveryTimeBeforeAndDeliveryStatus(OffsetDateTime time, DeliveryStatusEnumDomain status);



    List<Delivery> findByStatus(String status);

    List<Delivery> findLateDeliveries();

    List<Delivery> findByDeliveryAreaId(Integer deliveryAreaId);


    List<Delivery> findByCourier(Courier courier);
    List<Delivery> findByOrders(Orders orders);
    List<Delivery> findByActualDeliveryDateTimeBefore(OffsetDateTime dateTime);
    List<Delivery> findByEstimatedDeliveryTimeAfter(OffsetDateTime dateTime);

    List<Delivery> findByStatus(DeliveryStatusEnumDomain status);


    List<Delivery> findLateDeliveries(OffsetDateTime estimatedTime);
}
