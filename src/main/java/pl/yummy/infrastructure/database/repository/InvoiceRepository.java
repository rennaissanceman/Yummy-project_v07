package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.InvoiceDAO;
import pl.yummy.domain.Invoice;
import pl.yummy.infrastructure.database.repository.jpa.InvoiceJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.InvoiceEntityMapper;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class InvoiceRepository implements InvoiceDAO {

    private final InvoiceJpaRepository invoiceJpaRepository;
    private final InvoiceEntityMapper invoiceEntityMapper;

    @Override
    public Optional<Invoice> findByInvoiceNumber(String invoiceNumber) {
        return invoiceJpaRepository.findByInvoiceNumber(invoiceNumber)
                .map(invoiceEntityMapper::mapFromEntity);
    }

    @Override
    public List<Invoice> findByBillingInformation_Customer_CustomerId(Long customerId) {
        return invoiceJpaRepository.findByBillingInformation_Customer_CustomerId(customerId)
                .stream()
                .map(invoiceEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findByIssueDateAfter(OffsetDateTime issueDate) {
        return invoiceJpaRepository.findByIssueDateAfter(issueDate)
                .stream()
                .map(invoiceEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findByTotalAmountGreaterThanEqual(BigDecimal totalAmount) {
        return invoiceJpaRepository.findByTotalAmountGreaterThanEqual(totalAmount)
                .stream()
                .map(invoiceEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Invoice> findByDueDate(OffsetDateTime dueDate) {
        return invoiceJpaRepository.findByDueDate(dueDate)
                .stream()
                .map(invoiceEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
