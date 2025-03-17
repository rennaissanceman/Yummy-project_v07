package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.PaymentDAO;
import pl.yummy.domain.Payment;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentDAO paymentDAO;

    @Transactional(readOnly = true)
    public Optional<Payment> findByTransactionId(String transactionId) {
        return paymentDAO.findByTransactionId(transactionId);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByOrdersId(Long ordersId) {
        return paymentDAO.findByOrders_OrdersId(ordersId);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByPaymentStatus(PaymentStatusEnumDomain paymentStatus) {
        return paymentDAO.findByPaymentStatus(paymentStatus);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByCreatedAtAfter(OffsetDateTime createdAt) {
        return paymentDAO.findByCreatedAtAfter(createdAt);
    }

    @Transactional(readOnly = true)
    public List<Payment> findByAmountGreaterThanEqual(Double amount) {
        return paymentDAO.findByAmountGreaterThanEqual(amount);
    }
}

    /*
    PaymentService

    - Odpowiada za obsługę płatności – wyszukiwanie płatności po identyfikatorze transakcji, dla konkretnego zamówienia,
    według statusu, daty lub kwoty.
    - Wstrzykiwany komponent: PaymentDAO.
    */
