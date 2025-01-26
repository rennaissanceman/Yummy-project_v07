package pl.yummy.business.dao;

import pl.yummy.domain.Payment;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentDAO {

    // Find a payment by its transaction ID
    Optional<Payment> findByTransactionId(String transactionId);

    // Find all payments by a specific order ID
    List<Payment> findByOrders_OrdersId(Integer ordersId);

    // Find all payments by their status
    List<Payment> findByPaymentStatus(PaymentStatusEnumDomain paymentStatus);

    // Find payments made after a specific date
    List<Payment> findByCreatedAtAfter(OffsetDateTime createdAt);

    // Find payments with an amount greater than or equal to a specified value
    List<Payment> findByAmountGreaterThanEqual(Double amount);


}
