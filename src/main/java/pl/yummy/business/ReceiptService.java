package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.ReceiptDAO;
import pl.yummy.domain.Receipt;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReceiptService {

    private final ReceiptDAO receiptDAO;

    @Transactional(readOnly = true)
    public Optional<Receipt> findByReceiptNumber(String receiptNumber) {
        return receiptDAO.findByReceiptNumber(receiptNumber);
    }

    @Transactional(readOnly = true)
    public List<Receipt> findByOrderId(Integer ordersId) {
        return receiptDAO.findByOrders_OrdersId(ordersId);
    }

    @Transactional(readOnly = true)
    public List<Receipt> findIssuedAfter(OffsetDateTime issueDate) {
        return receiptDAO.findByIssueDateAfter(issueDate);
    }

    @Transactional(readOnly = true)
    public List<Receipt> findByTotalAmount(Double totalAmount) {
        return receiptDAO.findByTotalAmountGreaterThanEqual(totalAmount);
    }

    @Transactional(readOnly = true)
    public List<Receipt> findByNotes(String keyword) {
        return receiptDAO.findByNotesContainingIgnoreCase(keyword);
    }
}

    /*
    ReceiptService

    - Zarządza paragonami/rachunkami – wyszukiwanie po numerze, dla danego zamówienia, według daty wydania,
    wartości oraz zawartości notatek.
    - Wstrzykiwany komponent: ReceiptDAO.
    */