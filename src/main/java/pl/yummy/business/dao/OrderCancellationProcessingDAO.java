package pl.yummy.business.dao;

import pl.yummy.domain.OrderCancellationRequest;

public interface OrderCancellationProcessingDAO {

    void cancelOrder(OrderCancellationRequest request);
}
