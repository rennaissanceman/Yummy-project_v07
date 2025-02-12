package pl.yummy.business.dao.processing;

import pl.yummy.domain.Courier;
import pl.yummy.domain.Orders;
import pl.yummy.domain.OrdersItem;
import pl.yummy.domain.Payment;

public interface OrderProcessingDAO {

    void process2(Orders orders, Courier courier, OrdersItem ordersItem);

    void process(Orders order, Courier courier);

    void process(Orders order, Courier courier, OrdersItem ordersItem);

    void process3(Orders order, Courier courier);

    void process3(Orders order, Courier courier, OrdersItem ordersItem);

//    zarządza procesowaniem zamówień
}
