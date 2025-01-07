package pl.yummy.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.yummy.infrastructure.database.entity.InvoiceEntity;

import java.time.OffsetDateTime;
import java.util.List;

public interface InvoiceDAO {

    // Niestandardowe metody
    List<InvoiceEntity> findByOrderId(Long orderId);
    List<InvoiceEntity> findByCustomerId(Long customerId);
    List<InvoiceEntity> findByDueDate(OffsetDateTime dueDate);

    @Query("SELECT i FROM InvoiceEntity i WHERE i.dateIssued BETWEEN :startDate AND :endDate")
    List<InvoiceEntity> findByDateIssuedBetween(@Param("startDate") OffsetDateTime startDate, @Param("endDate") OffsetDateTime endDate);
}
