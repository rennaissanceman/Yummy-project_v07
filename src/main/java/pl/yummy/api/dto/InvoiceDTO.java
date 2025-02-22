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
public class InvoiceDTO {

//    Przekazuje dane faktury

    Long invoiceId;
    String invoiceNumber;
    OffsetDateTime issueDate;
    OffsetDateTime saleDate;
    BigDecimal totalAmount;
    BigDecimal netAmount;
    BigDecimal taxAmount;
    BigDecimal taxRate;
    OffsetDateTime dueDate; //(opcjonalnie)
    String issuerSignature; //(opcjonalnie)
}
