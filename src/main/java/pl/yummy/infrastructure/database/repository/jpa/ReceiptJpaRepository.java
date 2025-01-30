package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.ReceiptEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptJpaRepository extends JpaRepository<ReceiptEntity, Long> {

    // Find a receipt by its unique receipt number
    Optional<ReceiptEntity> findByReceiptNumber(String receiptNumber);

    // Find all receipts for a specific order ID
    List<ReceiptEntity> findByOrders_OrdersId(Integer ordersId);

    // Find all receipts issued after a specific date
    List<ReceiptEntity> findByIssueDateAfter(OffsetDateTime issueDate);

    // Find receipts by total amount greater than or equal to a specified value
    List<ReceiptEntity> findByTotalAmountGreaterThanEqual(Double totalAmount);

    // Find receipts with specific notes
    List<ReceiptEntity> findByNotesContainingIgnoreCase(String keyword);
}
