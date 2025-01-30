package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.InvoiceEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Long> {


    // Find an invoice by its unique invoice number
    Optional<InvoiceEntity> findByInvoiceNumber(String invoiceNumber);

    // Find all invoices for a specific customer ID
    List<InvoiceEntity> findByBillingInformation_Customer_CustomerId(Long customerId);

    // Find all invoices issued after a specific date
    List<InvoiceEntity> findByIssueDateAfter(OffsetDateTime issueDate);

    // Find invoices with a total amount greater than or equal to a specified value
    List<InvoiceEntity> findByTotalAmountGreaterThanEqual(BigDecimal totalAmount);

    // Find invoices by their due date
    List<InvoiceEntity> findByDueDate(OffsetDateTime dueDate);

/*
    // Niestandardowe metody
    List<InvoiceEntity> findByOrderId(Long orderId);
    List<InvoiceEntity> findByCustomerId(Long customerId);
    List<InvoiceEntity> findByDueDate(OffsetDateTime dueDate);

    @Query("SELECT i FROM InvoiceEntity i WHERE i.dateIssued BETWEEN :startDate AND :endDate")
    List<InvoiceEntity> findByDateIssuedBetween(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);
    */
}
