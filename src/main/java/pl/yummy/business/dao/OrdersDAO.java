package pl.yummy.business.dao;

import pl.yummy.domain.Orders;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersDAO {

    // Find an order by its unique order number
    Optional<Orders> findByOrdersNumber(String ordersNumber);

    // Find all orders for a specific customer ID
    List<Orders> findByCustomer_CustomerId(Long customerId);

    // Find all orders by status
    List<Orders> findByOrdersStatus(OrdersStatusEnumDomain ordersStatus);

    // Find orders created after a specific date
    List<Orders> findByOrdersDateTimeAfter(OffsetDateTime dateTime);

    // Find orders by total amount greater than or equal to a specified value
    List<Orders> findByTotalAmountGreaterThanEqual(BigDecimal totalAmount);

    List<Orders> findOrdersWithoutDelivery();

    List<Orders> findByOrdersDateTimeBetween(OffsetDateTime startDate, OffsetDateTime endDate);

    Orders save(Orders orders);

    List<Orders> findAll();

}


