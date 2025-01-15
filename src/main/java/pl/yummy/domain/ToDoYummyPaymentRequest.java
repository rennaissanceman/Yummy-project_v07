package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Value
@Builder
public class ToDoYummyPaymentRequest {

//    Cel: Reprezentacja danych związanych z przetwarzaniem płatności.

//    Zwiększa modularność systemu i upraszcza zarządzanie transakcjami płatniczymi.

    String orderId;
    BigDecimal amount;
    String paymentMethod; // Np. "CARD", "CASH"
    String paymentStatus; // "PENDING", "COMPLETED", "FAILED"
    OffsetDateTime paymentDate;
}
