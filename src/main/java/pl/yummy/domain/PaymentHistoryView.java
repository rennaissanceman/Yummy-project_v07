package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
public class PaymentHistoryView {

    List<PaymentHistoryItem> payments;

    @With
    @Value
    @Builder
    public static class PaymentHistoryItem {
        OffsetDateTime paymentDate;
        BigDecimal amount;
        String paymentStatus;
        String transactionId;
    }
}

    /*
    Read Model / Value Object – zestawienie historii płatności.

    PaymentHistoryView.
    Model prezentujący historię płatności powiązanych z danym zamówieniem lub klientem.
    Może zawierać listę transakcji z datą, kwotą oraz statusem płatności.
    */
