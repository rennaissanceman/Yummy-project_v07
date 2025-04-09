package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {

//    Dla danych paragonu/faktury fiskalnej (jeśli rozdzielamy)
    Long receiptId;
    String receiptNumber;
    Long ordersId;
    String issueDate;
    String saleDate;
    BigDecimal totalAmount;
    BigDecimal netAmount;
    BigDecimal taxAmount;
    BigDecimal taxRate;
    String notes;
}
