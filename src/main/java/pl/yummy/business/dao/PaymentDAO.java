package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {

    /* CRUD */
    /* create */
    PaymentEntity createPayment(PaymentEntity payment);

    /* read */
    Optional<PaymentEntity> findPaymentById(Long paymentId);
    Optional<PaymentEntity> findPaymentByOrder(Long ordersId);
    List<PaymentEntity> findPaymentByStatus(List<String> paymentStatus);

    /* update */
    PaymentEntity updatePayment(PaymentEntity payment);

    /* delete */
    void deletePayment(Long paymentId);
}

