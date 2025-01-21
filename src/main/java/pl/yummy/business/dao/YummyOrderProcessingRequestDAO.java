package pl.yummy.business.dao;

import pl.yummy.domain.Courier;
import pl.yummy.domain.Orders;
import pl.yummy.domain.YummyOrderProcessingRequest;

import java.util.List;

public interface YummyOrderProcessingRequestDAO {

    void process(YummyOrderProcessingRequest orderProcessingRequest, Orders orders);

    void process(YummyOrderProcessingRequest orderProcessingRequest, Orders orders, Courier courier);

    List<YummyOrderProcessingRequest> findPendingOrders();

    List<YummyOrderProcessingRequest> findByDeliveryStatus(String deliveryStatus);

    List<YummyOrderProcessingRequest> findOrdersProcessedByUser(String userAuthId);
}
