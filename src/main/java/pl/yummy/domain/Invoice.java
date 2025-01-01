package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "invoiceId")
@ToString(of = {"invoiceId", "invoiceNumber", "orders", "issueDate", "saleDate", "totalAmount", "netAmount", "taxRate",
        "taxAmount", "payment", "billingInformation", "notes", "dueDate", "issuerSignature"})
public class Invoice {

    Integer invoiceId;
    String invoiceNumber;
    Orders orders;
    OffsetDateTime issueDate;
    OffsetDateTime saleDate;
    BigDecimal totalAmount;
    BigDecimal netAmount;
    BigDecimal taxAmount;
    BigDecimal taxRate;
    Payment payment;
    BillingInformation billingInformation;
    String notes;
    OffsetDateTime dueDate;
    String issuerSignature;

    public boolean isPaid() {
        return payment != null && payment.getPaymentStatus() == PaymentStatusEnumDomain.COMPLETED;
    }

}
