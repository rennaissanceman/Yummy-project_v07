package pl.yummy.infrastructure.database.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.ProcessingPaymentProcessingDAO;
import pl.yummy.domain.Payment;
import pl.yummy.domain.requests.PaymentProcessingRequest;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.PaymentEntity;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatusEnumEntity;
import pl.yummy.infrastructure.database.entity.enums.PaymentStatusEnumEntity;
import pl.yummy.infrastructure.database.repository.jpa.OrdersJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.PaymentJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.PaymentEntityMapper;

import java.time.OffsetDateTime;

@Repository
@AllArgsConstructor
public class ProcessingPaymentProcessingRepository implements ProcessingPaymentProcessingDAO {

    /*
    Repozytorium to przyjmuje obiekt typu PaymentProcessingRequest (DTO wejściowe) i inicjuje proces płatności.
    Mapuje dane na obiekt domenowy Payment, a następnie zapisuje encję do bazy.
    */

    private final PaymentJpaRepository paymentJpaRepository;
    private final OrdersJpaRepository ordersJpaRepository;

    private final PaymentEntityMapper paymentEntityMapper;

    @Override
    @Transactional
    public void processPayment(PaymentProcessingRequest request) {
        // Mapowanie danych z PaymentProcessingRequest na encję Payment
        Payment payment = Payment.builder()
                .paymentId(request.getOrderId())
                .paymentMethod(request.getPaymentMethod())
                .amount(request.getAmount())
                .transactionId(request.getTransactionId())
                .comment(request.getComment())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                // ustawienie dodatkowych pól (np. createdAt) w serwisie domenowym
                .build();

        var paymentEntity = paymentEntityMapper.mapToEntity(payment);
        paymentJpaRepository.saveAndFlush(paymentEntity);
    }

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

    – Odpowiada za inicjowanie procesu płatności. Repozytorium mapuje dane z obiektu PaymentProcessingRequest
    na encję płatności (PaymentEntity), ustawia odpowiednie statusy i zapisuje transakcję.
    - Obsługuje proces płatności i zwrotów w jednym miejscu.
    - Łatwość rozszerzenia o np. obsługę różnych metod płatności.

    Obsługa płatności --> Obsługa autoryzacji, zwrotów, aktualizacja statusów płatności
    */
}
