package pl.yummy.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.InvoiceDAO;
import pl.yummy.domain.Invoice;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceService {

    private final InvoiceDAO invoiceDAO;

    @Transactional(readOnly = true)
    public Optional<Invoice> findByInvoiceNumber(String invoiceNumber) {
        return invoiceDAO.findByInvoiceNumber(invoiceNumber);
    }

    @Transactional(readOnly = true)
    public List<Invoice> findByCustomerId(Long customerId) {
        return invoiceDAO.findByBillingInformation_Customer_CustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<Invoice> findIssuedAfter(OffsetDateTime issueDate) {
        return invoiceDAO.findByIssueDateAfter(issueDate);
    }

    @Transactional(readOnly = true)
    public List<Invoice> findByTotalAmount(BigDecimal totalAmount) {
        return invoiceDAO.findByTotalAmountGreaterThanEqual(totalAmount);
    }

    @Transactional(readOnly = true)
    public List<Invoice> findByDueDate(OffsetDateTime dueDate) {
        return invoiceDAO.findByDueDate(dueDate);
    }
}

    /*
    _____________________________________________________________
    InvoiceService

    - Odpowiada za zarządzanie fakturami – wyszukiwanie faktury po numerze, pobieranie faktur dla konkretnego klienta,
    filtrowanie po dacie wydania, wartości czy terminie płatności.
    - Wstrzykiwany komponent: InvoiceDAO.
    */