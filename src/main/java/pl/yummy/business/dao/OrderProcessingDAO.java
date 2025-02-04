package pl.yummy.business.dao;

import pl.yummy.domain.Orders;
import pl.yummy.domain.Payment;

public interface OrderProcessingDAO {

    void processOrderPayment(Orders orders, Payment payment);

//    zarządza procesowaniem zamówień
}
