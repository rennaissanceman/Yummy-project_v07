package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.PaymentEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {


    // Find a payment by its transaction ID
    Optional<PaymentEntity> findByTransactionId(String transactionId);

    // Find all payments by a specific order ID
    List<PaymentEntity> findByOrders_OrdersId(Long ordersId);

    // Find all payments by their status
    List<PaymentEntity> findByPaymentStatus(PaymentStatusEnumDomain paymentStatus);

    // Find payments made after a specific date
    List<PaymentEntity> findByCreatedAtAfter(OffsetDateTime createdAt);

    // Find payments with an amount greater than or equal to a specified value
    List<PaymentEntity> findByAmountGreaterThanEqual(Double amount);
/*
    Optional<PaymentEntity> findByOrderId(Long orderId);

    @Query("SELECT p FROM PaymentEntity p WHERE p.status IN :statuses")
    List<PaymentEntity> findByStatusIn(@Param("statuses") List<String> statuses);
    */
}
