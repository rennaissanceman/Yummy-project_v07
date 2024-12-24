package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.PaymentMethodEntity;
import pl.yummy.infrastructure.database.entity.enums.PaymentMethodStatus;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodDAO {


    /* CRUD */
    /* create */
    PaymentMethodEntity createPaymentMethod(PaymentMethodEntity paymentMethod);

    /* read */
    Optional<PaymentMethodEntity> findPaymentMethodById(Long paymentMethodId);
    List<PaymentMethodEntity> findAllPaymentMethods();
    List<PaymentMethodEntity> findPaymentMethodsByActiveStatus(Boolean isActive);
    List<PaymentMethodEntity> findPaymentMethodsByStatus(PaymentMethodStatus paymentMethodStatus);

    /* update */
    PaymentMethodEntity updatePaymentMethod(PaymentMethodEntity paymentMethod);

    /* delete */
    void deletePaymentMethod(Long paymentMethodId);


    boolean existsPaymentMethodByName(String paymentMethodName);
    long countActivePaymentMethods();

}
