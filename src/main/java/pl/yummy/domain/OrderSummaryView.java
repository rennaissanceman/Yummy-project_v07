package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
public class OrderSummaryView {

    Integer orderId; // ID zamówienia
    String orderNumber; // Numer zamówienia
    String customerName; // Imię i nazwisko klienta
    BigDecimal totalAmount; // Całkowita kwota zamówienia
    String status; // Status zamówienia (np. "DELIVERED", "PENDING")
    OffsetDateTime deliveryEstimatedTime; // Szacowany czas dostawy

    public boolean isPending() {
        return "PENDING".equalsIgnoreCase(status);
    }
}

    // Zapewnia podsumowanie zamówienia dla klienta, zawierając podstawowe informacje o zamówieniu.
    // Podsumowanie zamówienia – alternatywa dla YummyOrderSummary
