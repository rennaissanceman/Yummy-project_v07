package pl.yummy.business.dao;

import pl.yummy.domain.RequestOrderPlacement;

public interface ProcessingOrderPlacementDAO {

    void placeOrder(RequestOrderPlacement request);
}
