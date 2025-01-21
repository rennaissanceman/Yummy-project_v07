package pl.yummy.business.dao;

import pl.yummy.domain.MenuItem;
import pl.yummy.domain.Orders;
import pl.yummy.domain.OrdersItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrdersItemDAO {

    List<OrdersItem> findByOrdersId(Integer ordersId);
    List<OrdersItem> findByMenuItemId(Integer menuItemId);


    List<OrdersItem> findByOrderId(Integer orderId);

    List<OrdersItem> findExpensiveItems(Double minimumPrice);

    List<OrdersItem> findByOrders(Orders orders);
    List<OrdersItem> findByMenuItem(MenuItem menuItem);
    List<OrdersItem> findByTotalPriceGreaterThan(BigDecimal price);



    List<OrdersItem> findAbovePrice(BigDecimal price);
}
