package pl.yummy.business.dao;

import pl.yummy.domain.Invoice;
import pl.yummy.domain.Payment;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceDAO {

    // Find an invoice by its unique invoice number
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);

    // Find all invoices for a specific customer ID
    List<Invoice> findByBillingInformation_Customer_CustomerId(Long customerId);

    // Find all invoices issued after a specific date
    List<Invoice> findByIssueDateAfter(OffsetDateTime issueDate);

    // Find invoices with a total amount greater than or equal to a specified value
    List<Invoice> findByTotalAmountGreaterThanEqual(BigDecimal totalAmount);

    // Find invoices by their due date
    List<Invoice> findByDueDate(OffsetDateTime dueDate);






    List<Invoice> findByBillingInformationId(Integer billingInformationId);
    List<Invoice> findByIssueDateBetween(OffsetDateTime startDate, OffsetDateTime endDate);



}
