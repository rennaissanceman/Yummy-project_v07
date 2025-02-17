package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.OrdersDAO;
import pl.yummy.domain.Menu;
import pl.yummy.domain.Orders;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.MenuEntity;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatusEnumEntity;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.OrdersEntityMapper;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class OrdersRepository implements OrdersDAO {


    private final OrdersJpaRepository ordersJpaRepository;
    private final OrdersEntityMapper ordersEntityMapper;

    @Override
    public Optional<Orders> findByOrdersNumber(String ordersNumber) {
        return ordersJpaRepository.findByOrdersNumber(ordersNumber)
                .map(ordersEntityMapper::mapFromEntity);
    }

    @Override
    public List<Orders> findByCustomer_CustomerId(Long customerId) {
        return ordersJpaRepository.findByCustomer_CustomerId(customerId)
                .stream()
                .map(ordersEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }


    @Override // zły enum
    public List<Orders> findByOrdersStatus(OrdersStatusEnumDomain ordersStatus) {
        return ordersJpaRepository.findByOrdersStatus(
                OrdersStatusEnumEntity.valueOf(ordersStatus.name())
                )
                .stream()
                .map(ordersEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Orders> findByOrdersDateTimeAfter(OffsetDateTime dateTime) {
        return ordersJpaRepository.findByOrdersDateTimeAfter(dateTime)
                .stream()
                .map(ordersEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Orders> findByTotalAmountGreaterThanEqual(BigDecimal totalAmount) {
        return ordersJpaRepository.findByTotalAmountGreaterThanEqual(totalAmount)
                .stream()
                .map(ordersEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Orders> findOrdersWithoutDelivery() {
        return ordersJpaRepository.findOrdersWithoutDelivery()
                .stream()
                .map(ordersEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Orders save(Orders orders) {
        OrdersEntity toSave = ordersEntityMapper.mapToEntity(orders);
        OrdersEntity saved = ordersJpaRepository.save(toSave);
        return ordersEntityMapper.mapFromEntity(saved);
    }
}
