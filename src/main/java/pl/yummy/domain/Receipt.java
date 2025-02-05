package pl.yummy.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "receiptId")
@ToString(of = {"receiptId", "receiptNumber", "orders", "issueDate", "saleDate", "totalAmount", "netAmount", "taxAmount",
        "taxRate", "payment", "notes"})
public class Receipt {

    Long receiptId;
    String receiptNumber;
    Orders orders;
    OffsetDateTime issueDate;
    OffsetDateTime saleDate;
    BigDecimal totalAmount;
    BigDecimal netAmount;
    BigDecimal taxAmount;
    BigDecimal taxRate;
    Payment payment;
    String notes;
}
