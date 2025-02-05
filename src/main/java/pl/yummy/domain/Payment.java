package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "paymentId")
@ToString(of = {"paymentId", "orders", "paymentMethod", "amount", "paymentStatus", "transactionId", "createdAt", "updatedAt"})
public class Payment {

    Long paymentId;
    Orders orders;
    PaymentMethodStatusEnumDomain paymentMethod;
    BigDecimal amount;
    PaymentStatusEnumDomain paymentStatus;
    String transactionId;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    Invoice invoice;
    Receipt receipt;

    public boolean isSuccessful() {
        return paymentStatus == PaymentStatusEnumDomain.COMPLETED;
    }
}
