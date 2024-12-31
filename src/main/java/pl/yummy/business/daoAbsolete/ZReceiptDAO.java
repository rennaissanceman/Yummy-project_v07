package pl.yummy.business.daoAbsolete;
import pl.yummy.infrastructure.database.entity.ReceiptEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ZReceiptDAO {

    /* CRUD */
    /* create */
    ReceiptEntity createReceipt(ReceiptEntity receipt);

    /* read */
    Optional<ReceiptEntity> findReceiptById(Long receiptId);
    List<ReceiptEntity> findAllReceipts();
    Optional<ReceiptEntity> findReceiptByReceiptNumber(String receiptNumber);
    List<ReceiptEntity> findReceiptsByOrderId(Long ordersId);
    List<ReceiptEntity> findReceiptsByIssueDateRange(OffsetDateTime startDate, OffsetDateTime endDate);

    /* update */
    ReceiptEntity updateReceipt(ReceiptEntity receipt);

    /* delete */
    void deleteReceipt(Long receiptId);



    boolean existsReceiptByReceiptNumber(String receiptNumber);
    BigDecimal calculateTotalTaxAmountByDateRange(OffsetDateTime startDate, OffsetDateTime endDate);
    BigDecimal countReceiptsByDateRange(OffsetDateTime startDate, OffsetDateTime endDate);

}
