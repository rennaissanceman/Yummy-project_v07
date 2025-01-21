package pl.yummy.business.dao;

import pl.yummy.domain.Payment;

import java.util.List;

public interface PaymentDAO {

    Payment findByTransactionId(String transactionId);

    List<Payment> findByOrderId(Integer orderId);

    List<Payment> findByStatus(String status);

    List<Payment> findFailedPayments();
}
