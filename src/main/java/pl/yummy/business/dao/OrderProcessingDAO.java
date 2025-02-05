package pl.yummy.business.dao;

import pl.yummy.domain.Courier;
import pl.yummy.domain.Orders;
import pl.yummy.domain.OrdersItem;
import pl.yummy.domain.Payment;

public interface OrderProcessingDAO {

    void process(Orders order, Courier courier);

    void process(Orders order, Courier courier, OrdersItem ordersItem);

//    zarządza procesowaniem zamówień
}
