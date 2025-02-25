package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
public class ViewPaymentHistoryDTO {

    /*
    Agreguje historię płatności dla danego zamówienia lub klienta. Zawiera listę transakcji z datą, kwotą,
    statusem płatności oraz identyfikatorem transakcji. Taki widok jest użyteczny do monitorowania przebiegu płatności
    oraz analizy historii finansowej.
    */

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
