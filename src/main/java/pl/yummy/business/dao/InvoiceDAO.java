package pl.yummy.business.dao;

import pl.yummy.domain.Invoice;
import pl.yummy.domain.Payment;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceDAO {

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findByBillingInformationId(Integer billingInformationId);
    List<Invoice> findByIssueDateBetween(OffsetDateTime startDate, OffsetDateTime endDate);



    List<Invoice> findByCustomerId(Integer customerId);

    List<Invoice> findByDateRange(String startDate, String endDate);


    List<Invoice> findByTotalAmountGreaterThan(BigDecimal amount);
    List<Invoice> findByPayment(Payment payment);

    List<Invoice> findBetweenDates(OffsetDateTime startDate, OffsetDateTime endDate);

    List<Invoice> findAboveAmount(BigDecimal amount);
}
