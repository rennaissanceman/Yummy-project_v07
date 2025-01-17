package pl.yummy.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "orderId")
@ToString(of = {"orderId", "orderNumber", "customerName", "totalAmount", "status", "deliveryEstimatedTime"})
public class YummyOrderSummary {

    // Zapewnia podsumowanie zamówienia dla klienta, zawierając podstawowe informacje o zamówieniu.

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
