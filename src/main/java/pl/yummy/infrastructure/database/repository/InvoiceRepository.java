package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.InvoiceDAO;
import pl.yummy.infrastructure.database.entity.InvoiceEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class InvoiceRepository implements InvoiceDAO {
    @Override
    public List<InvoiceEntity> findByOrderId(Long orderId) {
        return null;
    }

    @Override
    public List<InvoiceEntity> findByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<InvoiceEntity> findByDueDate(OffsetDateTime dueDate) {
        return null;
    }

    @Override
    public List<InvoiceEntity> findByDateIssuedBetween(OffsetDateTime startDate, OffsetDateTime endDate) {
        return null;
    }
}
