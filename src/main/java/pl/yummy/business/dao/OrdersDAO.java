package pl.yummy.business.dao;

import pl.yummy.domain.Customer;
import pl.yummy.domain.Orders;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public interface OrdersDAO {

    List<Orders> findByCustomerId(Integer customerId);
    List<Orders> findByOrdersStatus(OrdersStatusEnumDomain status);
    List<Orders> findByTotalAmountGreaterThan(BigDecimal minAmount);



    List<Orders> findByStatus(String status);

    List<Orders> findByDateRange(String startDate, String endDate);

    List<Orders> findByDeliveryAreaId(Integer deliveryAreaId);


    List<Orders> findByCustomer(Customer customer);

    List<Orders> findByOrdersDateTimeBetween(OffsetDateTime start, OffsetDateTime end);


    List<Orders> findByStatus(OrdersStatusEnumDomain status);

    List<Orders> findByDateRange(OffsetDateTime start, OffsetDateTime end);

    List<Orders> findAboveAmount(BigDecimal amount);

}
