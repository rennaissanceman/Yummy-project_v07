package pl.yummy.business.dao.processing;

import pl.yummy.domain.requests.OrderPlacementRequest;

public interface OrderPlacementDAO {

    void placeOrder(OrderPlacementRequest request);
}
