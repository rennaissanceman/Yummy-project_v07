package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Value
@Builder
public class YummyPaymentRequest {

    String orderId;
    BigDecimal amount;
    String paymentMethod; // Np. "CARD", "CASH"
    String paymentStatus; // "PENDING", "COMPLETED", "FAILED"
    OffsetDateTime paymentDate;
}
