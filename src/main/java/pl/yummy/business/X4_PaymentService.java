package pl.yummy.business;

public class X4_PaymentService {
}

    /*
    4. Serwis obsługi płatności.

    Odpowiada za:

    - Tworzenie płatności dla zamówienia.
    - Obsługę transakcji (akceptacja, zwrot, statusy).
    - Powiązanie płatności z zamówieniem.


    Metody:

    processPayment(PaymentProcessingRequest request) → PaymentResponse
    refundPayment(Long paymentId) → void
    checkPaymentStatus(Long paymentId) → PaymentStatusEnumDomain
    */
