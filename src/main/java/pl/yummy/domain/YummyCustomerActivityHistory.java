package pl.yummy.domain;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "customerId")
@ToString(of = {"customerId", "customerName", "customerSurname", "totalOrders", "totalSpent", "recentOrders"})
public class YummyCustomerActivityHistory {

    Integer customerId; // Identyfikator klienta
    String customerName; // Imię klienta
    String customerSurname; // Nazwisko klienta
    Integer totalOrders; // Całkowita liczba zamówień
    BigDecimal totalSpent; // Łączna kwota wydana przez klienta
    List<RecentOrder> recentOrders; // Lista ostatnich zamówień klienta

    /**
     * Klasa wewnętrzna reprezentująca dane jednego zamówienia w historii.
     */
    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "orderId")
    @ToString(of = {"orderId", "orderDate", "status", "amount"})
    public static class RecentOrder {
        Integer orderId; // Identyfikator zamówienia
        OffsetDateTime orderDate; // Data zamówienia
        String status; // Status zamówienia (np. PENDING, DELIVERED)
        BigDecimal amount; // Kwota zamówienia
    }

    /**
     * Oblicza średnią wartość zamówień.
     *
     * @return średnia wartość zamówienia lub null, jeśli brak zamówień.
     */
    public BigDecimal calculateAverageOrderValue() {
        if (totalOrders == null || totalOrders == 0 || totalSpent == null) {
            return null;
        }
        return totalSpent.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
    }
}
