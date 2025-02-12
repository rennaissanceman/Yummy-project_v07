package pl.yummy.domain.view;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
public class CustomerActivityHistoryView {

    Integer customerId;            // Identyfikator klienta
    String customerName;           // Imię klienta
    String customerSurname;        // Nazwisko klienta
    Integer totalOrders;           // Całkowita liczba zamówień
    BigDecimal totalSpent;         // Łączna kwota wydana przez klienta
    List<RecentOrder> recentOrders; // Lista ostatnich zamówień klienta


    /*
    Klasa wewnętrzna reprezentująca dane jednego zamówienia w historii.
    */
    @With
    @Value
    @Builder
    public static class RecentOrder {
        Integer orderId;              // Identyfikator zamówienia
        OffsetDateTime orderDate;     // Data zamówienia
        String status;                // Status zamówienia (np. PENDING, DELIVERED)
        BigDecimal amount;            // Kwota zamówienia
    }


    /*
     Oblicza średnią wartość zamówień.
     @return średnia wartość zamówienia lub null, jeśli brak zamówień.
     */
    public BigDecimal calculateAverageOrderValue() {
        if (totalOrders == null || totalOrders == 0 || totalSpent == null) {
            return BigDecimal.ZERO;
        }
        return totalSpent.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
    }
}
