package pl.yummy.domain;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.OrderProcessingDAO;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.PaymentEntity;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatus;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.PaymentJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.PaymentEntityMapper;

@Repository
@AllArgsConstructor
public class OrderProcessingRepository implements OrderProcessingDAO {

    private final OrdersJpaRepository ordersJpaRepository;
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentEntityMapper paymentEntityMapper;

    @Override
    @Transactional
    public void processOrderPayment(
            Orders orders,
            Payment payment
    ) {
        OrdersEntity ordersEntity = ordersJpaRepository.findById(Long.valueOf(orders.getOrdersId()))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        PaymentEntity paymentEntity = paymentEntityMapper.mapToEntity(payment);
        paymentEntity.setOrders(ordersEntity);

        paymentJpaRepository.saveAndFlush(paymentEntity);

        ordersEntity.setOrdersStatus(OrdersStatus.CONFIRMED); //PAID
        ordersJpaRepository.saveAndFlush(ordersEntity);
    }
}
