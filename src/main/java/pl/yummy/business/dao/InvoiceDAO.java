package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.InvoiceEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface InvoiceDAO {

    /* CRUD */
    /* create */
    InvoiceEntity createInvoice(InvoiceEntity invoice);

    /* read */
    Optional<InvoiceEntity> findInvoiceById(Long invoiceId);
    List<InvoiceEntity> findInvoicesByOrderId(Long ordersId);
    List<InvoiceEntity> findInvoicesByCustomerId(Long customerId);
    List<InvoiceEntity> findInvoicesByDueDate(OffsetDateTime dueDate);
    List<InvoiceEntity> findInvoicesWithinDateRange(OffsetDateTime startDate, OffsetDateTime endDate);
    List<InvoiceEntity> findAllInvoices();

    /* update */
    InvoiceEntity updateInvoice(InvoiceEntity invoice);

    /* delete */
    void deleteInvoice(Long invoiceId);

}

