package pl.yummy.api.dto;

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
public class ViewCustomerActivityHistoryDTO {

    /*
     Reprezentuje historię aktywności klienta, wraz z listą ostatnich zamówień.
     Prezentuje historię aktywności klienta. Zawiera takie dane jak całkowita liczba zamówień, łączna kwota wydana przez
      klienta oraz listę ostatnich zamówień. Dzięki temu widokowi można wyświetlić klientowi lub administratorowi
      podsumowanie jego aktywności, w tym obliczenia, takie jak średnia wartość zamówienia.
     */

    Long customerId;
    String customerName;
    String customerSurname;
    Integer totalOrders;
    BigDecimal totalSpent;
    List<RecentOrder> recentOrders;

    @With
    @Value
    @Builder
    public static class RecentOrder {
        Integer orderId;
        OffsetDateTime orderDate;
        String status;
        BigDecimal amount;
    }

    public BigDecimal calculateAverageOrderValue() {
        if (totalOrders == null || totalOrders == 0 || totalSpent == null) {
            return BigDecimal.ZERO;
        }
        return totalSpent.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
    }
}
