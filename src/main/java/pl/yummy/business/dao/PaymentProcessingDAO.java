package pl.yummy.business.dao;

import pl.yummy.domain.Payment;
import pl.yummy.domain.PaymentProcessingRequest;

public interface PaymentProcessingDAO {

    /*
    Obsługa procesów płatności
    Cel: Zarządzanie płatnościami, np. autoryzacja, zwroty, zmiany statusu.

    */

    void processPayment(PaymentProcessingRequest request);

    void processPayment(Long orderId, Payment payment);

    void refundPayment(Long paymentId);
}
