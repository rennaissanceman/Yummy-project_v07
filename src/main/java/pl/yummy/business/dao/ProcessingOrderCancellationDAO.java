package pl.yummy.business.dao;

import pl.yummy.domain.RequestOrderCancellation;

public interface ProcessingOrderCancellationDAO {

    void cancelOrder(RequestOrderCancellation request);
}
