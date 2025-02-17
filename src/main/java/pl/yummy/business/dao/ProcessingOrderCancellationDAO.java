package pl.yummy.business.dao;

import pl.yummy.domain.requests.OrderCancellationRequest;

public interface ProcessingOrderCancellationDAO {

    void cancelOrder(OrderCancellationRequest request);
}
