package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "processingId")
@ToString(of = {"processingId", "orders", "deliveryStatus", "paymentStatus", "processingStartTime", "processingEndTime"})
public class YummyOrderProcessingRequest {

    Integer processingId; // Unikalny identyfikator procesu przetwarzania zamówienia
    Orders orders; // Zamówienie, które jest przetwarzane
    DeliveryStatusEnumDomain deliveryStatus; // Status dostawy zamówienia
    PaymentStatusEnumDomain paymentStatus; // Status płatności zamówienia
    OffsetDateTime processingStartTime; // Czas rozpoczęcia przetwarzania
    OffsetDateTime processingEndTime; // Czas zakończenia przetwarzania
    String processingNotes; // Notatki dotyczące procesu przetwarzania

    /**
     * Sprawdza, czy zamówienie można przetwarzać.
     *
     * @return true, jeśli zamówienie ma odpowiedni status i spełnia warunki przetwarzania.
     */
    public boolean canProcess() {
        return orders != null
                && orders.getOrdersStatus() == OrdersStatusEnumDomain.PENDING
                && orders.getPayment() != null
                && orders.getPayment().getPaymentStatus() == PaymentStatusEnumDomain.IN_PROGRESS;
    }

    /**
     * Zaznacza zamówienie jako przetworzone.
     *
     * @return nowe przetwarzanie zaktualizowane o zakończony status.
     */
    public YummyOrderProcessingRequest markAsProcessed() {
        return this.withDeliveryStatus(DeliveryStatusEnumDomain.DELIVERED)
                .withProcessingEndTime(OffsetDateTime.now());
    }

    /**
     * Oblicza czas przetwarzania zamówienia.
     *
     * @return czas przetwarzania w sekundach.
     */
    public long calculateProcessingTime() {
        if (processingStartTime == null || processingEndTime == null) {
            return 0;
        }
        return processingEndTime.toEpochSecond() - processingStartTime.toEpochSecond();
    }

    /**
     * Sprawdza, czy płatność za zamówienie została zakończona.
     *
     * @return true, jeśli status płatności to COMPLETED.
     */
    public boolean isPaymentCompleted() {
        return paymentStatus == PaymentStatusEnumDomain.COMPLETED;
    }
}
