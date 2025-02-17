package pl.yummy.business.dao;

import pl.yummy.domain.Courier;
import pl.yummy.domain.Orders;
import pl.yummy.domain.OrdersItem;

public interface ProcessingOrderProcessingDAO {

    void process2(Orders orders, Courier courier, OrdersItem ordersItem);

    void process(Orders order, Courier courier);

    void process(Orders order, Courier courier, OrdersItem ordersItem);

    void process3(Orders order, Courier courier);

    void process3(Orders order, Courier courier, OrdersItem ordersItem);

//    zarządza procesowaniem zamówień
}
