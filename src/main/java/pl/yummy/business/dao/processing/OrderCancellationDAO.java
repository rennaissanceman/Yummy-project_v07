package pl.yummy.business.dao.processing;

import pl.yummy.domain.requests.OrderCancellationRequest;

public interface OrderCancellationDAO {

    void cancelOrder(OrderCancellationRequest request);
}
