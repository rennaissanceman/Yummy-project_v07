package pl.yummy.infrastructure.database.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.PaymentProcessingDAO;
import pl.yummy.domain.Payment;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.PaymentEntity;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatusEnumEntity;
import pl.yummy.infrastructure.database.entity.enums.PaymentStatusEnumEntity;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.PaymentJpaRepository;

@Repository
@AllArgsConstructor
public class PaymentProcessingRepository implements PaymentProcessingDAO {

    private final PaymentJpaRepository paymentJpaRepository;
    private final OrdersJpaRepository ordersJpaRepository;

    @Override
    @Transactional
    public void processPayment(Long orderId, Payment payment) {
        OrdersEntity ordersEntity = ordersJpaRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setOrders(ordersEntity);
        paymentEntity.setAmount(payment.getAmount());
        paymentEntity.setPaymentStatus(PaymentStatusEnumEntity.PENDING);

        paymentJpaRepository.save(paymentEntity);

        ordersEntity.setOrdersStatus(OrdersStatusEnumEntity.PAID);
        ordersJpaRepository.save(ordersEntity);
    }

    @Override
    @Transactional
    public void refundPayment(Long paymentId) {
        PaymentEntity paymentEntity = paymentJpaRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        paymentEntity.setPaymentStatus(PaymentStatusEnumEntity.REFUNDED);
        paymentJpaRepository.save(paymentEntity);
    }

    /*
    - Obsługuje proces płatności i zwrotów w jednym miejscu.
    - Łatwość rozszerzenia o np. obsługę różnych metod płatności.

    Obsługa płatności --> Obsługa autoryzacji, zwrotów, aktualizacja statusów płatności
    */
}
