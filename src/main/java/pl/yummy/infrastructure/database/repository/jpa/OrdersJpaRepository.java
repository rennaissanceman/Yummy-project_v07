package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.OrdersEntity;

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
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "customer",
                    "delivery",
                    "menu",
                    "payment",
                    "ordersItems"
            }
    )
    List<OrdersEntity> findByOrdersStatus(OrdersStatusEnumDomain ordersStatus);

    // Find orders created after a specific date
    List<OrdersEntity> findByOrdersDateTimeAfter(OffsetDateTime dateTime);

    // Find orders by total amount greater than or equal to a specified value
    List<OrdersEntity> findByTotalAmountGreaterThanEqual(BigDecimal totalAmount);


    @Query("""
    SELECT o FROM OrdersEntity o
    LEFT JOIN FETCH o.delivery d
    WHERE d.orders.ordersId IS NULL
    """)
    List<OrdersEntity> findOrdersWithoutDelivery();


    @Query("""
        SELECT o FROM OrdersEntity o 
        WHERE o.ordersDateTime BETWEEN :startDate AND :endDate
    """)
    List<OrdersEntity> findOrdersBetweenDates(
            @Param("startDate") OffsetDateTime startDate,
            @Param("endDate") OffsetDateTime endDate
    );

    @Modifying
    @Transactional
    @Query("""
        UPDATE OrdersEntity o 
        SET o.ordersStatus = :status 
        WHERE o.ordersId = :ordersId
    """)
    int updateOrderStatus(
            @Param("ordersId") Long ordersId,
            @Param("status") OrdersStatusEnumDomain status
    );

/*
    // Niestandardowa metoda
    List<OrdersEntity> findByCustomerId(Long customerId);
    */

}
