package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.OrdersDAO;
import pl.yummy.domain.Orders;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.OrdersEntityMapper;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrdersRepository implements OrdersDAO {


    private final OrdersJpaRepository ordersJpaRepository;
    private final OrdersEntityMapper mapper;

    @Override
    public Optional<Orders> findByOrdersNumber(String ordersNumber) {
        return ordersJpaRepository.findByOrdersNumber(ordersNumber)
                .map(mapper::mapFromEntity);
    }

    @Override
    public List<Orders> findByCustomer_CustomerId(Long customerId) {
        return ordersJpaRepository.findByCustomer_CustomerId(customerId).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Orders> findByOrdersStatus(OrdersStatusEnumDomain ordersStatus) {
        return ordersJpaRepository.findByOrdersStatus(ordersStatus).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Orders> findByOrdersDateTimeAfter(OffsetDateTime dateTime) {
        return ordersJpaRepository.findByOrdersDateTimeAfter(dateTime).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Orders> findByTotalAmountGreaterThanEqual(BigDecimal totalAmount) {
        return ordersJpaRepository.findByTotalAmountGreaterThanEqual(totalAmount).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }


    @Override
    public List<Orders> findOrdersWithoutDelivery() {
        return ordersJpaRepository.findOrdersWithoutDelivery().stream()
                .map(mapper::mapFromEntity)
                .toList();
    }
}
