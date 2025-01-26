package pl.yummy.business.dao;

import pl.yummy.domain.Receipt;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ReceiptDAO {

    // Find a receipt by its unique receipt number
    Optional<Receipt> findByReceiptNumber(String receiptNumber);

    // Find all receipts for a specific order ID
    List<Receipt> findByOrders_OrdersId(Integer ordersId);

    // Find all receipts issued after a specific date
    List<Receipt> findByIssueDateAfter(OffsetDateTime issueDate);

    // Find receipts by total amount greater than or equal to a specified value
    List<Receipt> findByTotalAmountGreaterThanEqual(Double totalAmount);

    // Find receipts with specific notes
    List<Receipt> findByNotesContainingIgnoreCase(String keyword);

}
