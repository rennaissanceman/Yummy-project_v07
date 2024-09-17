package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class zReceiptEntity {

    private Integer receiptId;
    private zOrderEntity order;
    private OffsetDateTime issueDate;
    private OffsetDateTime saleDate;
    private BigDecimal totalAmount;
    private BigDecimal netAmount;
    private BigDecimal taxAmount;
    private BigDecimal taxRate;
    private String paymentMethod;
    private String paymentStatus;
    private String notes;

}
