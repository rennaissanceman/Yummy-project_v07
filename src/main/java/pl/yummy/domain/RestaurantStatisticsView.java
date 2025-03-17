package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@With
@Value
@Builder
public class RestaurantStatisticsView {

    // Zbiera dane analityczne dotyczące restauracji, takie jak średnie oceny,
    // liczba zamówień czy najpopularniejsze pozycje w menu.

    Integer restaurantId;       // ID restauracji
    String restaurantName;      // Nazwa restauracji
    Integer totalOrders;        // Łączna liczba zamówień
    BigDecimal totalRevenue;    // Łączny przychód restauracji
    BigDecimal averageOrderValue; // Średnia wartość zamówienia
    List<String> topSellingItems; // Lista najpopularniejszych pozycji w menu

    public BigDecimal calculateAverageOrderValue() {
        if (totalOrders == null || totalOrders == 0 || totalRevenue == null) {
            return BigDecimal.ZERO;
        }
        return totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
    }
}
