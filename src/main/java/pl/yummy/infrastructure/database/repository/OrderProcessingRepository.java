package pl.yummy.infrastructure.database.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.OrderProcessingDAO;
import pl.yummy.domain.Orders;
import pl.yummy.domain.Payment;
import pl.yummy.domain.enums.CourierStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.CourierEntity;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.PaymentEntity;
import pl.yummy.infrastructure.database.entity.enums.CourierStatusEnumEntity;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatusEnumEntity;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatusEnumEntity;
import pl.yummy.infrastructure.database.repository.jpa.CourierJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.DeliveryJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.PaymentJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.DeliveryEntityMapper;
import pl.yummy.infrastructure.database.repository.mapper.PaymentEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderProcessingRepository implements OrderProcessingDAO {

    private final OrdersJpaRepository ordersJpaRepository;
    private final PaymentJpaRepository paymentJpaRepository;
    private final DeliveryJpaRepository deliveryJpaRepository;
    private final CourierJpaRepository courierJpaRepository;

    private final PaymentEntityMapper paymentEntityMapper;
    private final DeliveryEntityMapper deliveryEntityMapper;

    @Override
    @Transactional
    public void processOrderPayment(Orders orders, Payment payment) {
        OrdersEntity ordersEntity = ordersJpaRepository.findById(Long.valueOf(orders.getOrdersId()))
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        // Obsługa płatności
        PaymentEntity paymentEntity = paymentEntityMapper.mapToEntity(payment);
        paymentEntity.setOrders(ordersEntity);
        paymentJpaRepository.save(paymentEntity);

        // Aktualizacja statusu zamówienia na "PAID"
        ordersEntity.setOrdersStatus(OrdersStatusEnumEntity.PAID);
        ordersJpaRepository.save(ordersEntity);

        // Tworzenie dostawy dla zamówienia
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setOrders(ordersEntity);
        deliveryEntity.setDeliveryStatus(DeliveryStatusEnumEntity.PENDING);
        deliveryJpaRepository.save(deliveryEntity);

        // Przypisanie kuriera
        Optional<CourierEntity> availableCourier = courierJpaRepository.findFirstByCourierStatus(CourierStatusEnumDomain.AVAILABLE);

        availableCourier.ifPresent(courier -> {
            deliveryEntity.setCourier(courier);
            deliveryEntity.setDeliveryStatus(DeliveryStatusEnumEntity.ASSIGNED);
            courier.setCourierStatus(CourierStatusEnumEntity.BUSY);
            courierJpaRepository.save(courier);
        });

        deliveryJpaRepository.save(deliveryEntity);
    }
}
