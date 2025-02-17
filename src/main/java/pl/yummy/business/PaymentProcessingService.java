package pl.yummy.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.ProcessingPaymentProcessingDAO;
import pl.yummy.domain.Payment;
import pl.yummy.domain.requests.PaymentProcessingRequest;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentProcessingService {

    private final ProcessingPaymentProcessingDAO processingPaymentProcessingDAO;

    /**
     * Inicjuje proces płatności na podstawie danych przekazanych w obiekcie PaymentProcessingRequest.
     *
     * @param request obiekt żądania przetwarzania płatności zawierający m.in. numer zamówienia, metodę płatności, kwotę itp.
     */
    @Transactional
    public void processPayment(PaymentProcessingRequest request) {
        log.info("Rozpoczynam proces płatności dla zamówienia: {}", request.getOrderNumber());
        processingPaymentProcessingDAO.processPayment(request);
    }

    /**
     * Inicjuje proces płatności dla określonego zamówienia, korzystając z przekazanego obiektu Payment.
     *
     * @param orderId identyfikator zamówienia, dla którego realizowana jest płatność
     * @param payment obiekt płatności zawierający szczegóły transakcji
     */
    @Transactional
    public void processPayment(Long orderId, Payment payment) {
        log.info("Rozpoczynam proces płatności dla zamówienia o ID: {}", orderId);
        processingPaymentProcessingDAO.processPayment(orderId, payment);
    }

    /**
     * Realizuje procedurę zwrotu płatności dla danego identyfikatora płatności.
     *
     * @param paymentId identyfikator płatności, która ma zostać zwrócona
     */
    @Transactional
    public void refundPayment(Long paymentId) {
        log.info("Rozpoczynam procedurę zwrotu płatności dla płatności o ID: {}", paymentId);
        processingPaymentProcessingDAO.refundPayment(paymentId);
    }
}

    /*
    6. PaymentProcessingService.

    Cel:
    Obsługuje proces płatności – mapuje dane z PaymentProcessingRequest na obiekt domenowy Payment,
    zapisuje transakcję oraz umożliwia zwroty.
    */