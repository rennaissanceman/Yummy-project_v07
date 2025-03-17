package pl.yummy.business.dao;

import pl.yummy.domain.OrderPlacementRequest;

public interface OrderPlacementProcessingDAO {

    void placeOrder(OrderPlacementRequest request);
}
