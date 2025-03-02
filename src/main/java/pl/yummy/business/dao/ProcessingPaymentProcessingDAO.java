package pl.yummy.business.dao;

import pl.yummy.domain.Payment;
import pl.yummy.domain.RequestPaymentProcessing;

public interface ProcessingPaymentProcessingDAO {

    /*
    Obsługa procesów płatności
    Cel: Zarządzanie płatnościami, np. autoryzacja, zwroty, zmiany statusu.

    */

    void processPayment(RequestPaymentProcessing request);

    void processPayment(Long orderId, Payment payment);

    void refundPayment(Long paymentId);
}
