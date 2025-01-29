package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.domain.Orders;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersJpaRepository extends JpaRepository<OrdersEntity, Long> {

    // Find an order by its unique order number
    Optional<OrdersEntity> findByOrdersNumber(String ordersNumber);

    // Find all orders for a specific customer ID
    List<OrdersEntity> findByCustomer_CustomerId(Long customerId);

    // Find all orders by status
    List<OrdersEntity> findByOrdersStatus(OrdersStatusEnumDomain ordersStatus);

    // Find orders created after a specific date
    List<OrdersEntity> findByOrdersDateTimeAfter(OffsetDateTime dateTime);

    // Find orders by total amount greater than or equal to a specified value
    List<OrdersEntity> findByTotalAmountGreaterThanEqual(BigDecimal totalAmount);
/*
    // Niestandardowa metoda
    List<OrdersEntity> findByCustomerId(Long customerId);
    */

}
