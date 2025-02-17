package pl.yummy.business.dao;

import pl.yummy.domain.requests.OrderPlacementRequest;

public interface ProcessingOrderPlacementDAO {

    void placeOrder(OrderPlacementRequest request);
}
