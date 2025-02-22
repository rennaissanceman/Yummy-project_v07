package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@With
@Value
@Builder
public class ViewRestaurantStatisticsViewDTO {

    /*
     Model zbierający statystyki restauracji, np. łączną liczbę zamówień, przychody oraz najpopularniejsze pozycje menu.
     Agreguje dane analityczne dotyczące restauracji, takie jak łączna liczba zamówień, całkowity przychód,
     średnia wartość zamówienia oraz najpopularniejsze pozycje menu. Dzięki temu widokowi właściciel
     lub menedżer może ocenić wydajność restauracji i podejmować decyzje biznesowe na podstawie statystyk.
     */

    Integer restaurantId;
    String restaurantName;
    Integer totalOrders;
    BigDecimal totalRevenue;
    BigDecimal averageOrderValue;
    List<String> topSellingItems;

    public BigDecimal calculateAverageOrderValue() {
        if (totalOrders == null || totalOrders == 0 || totalRevenue == null) {
            return BigDecimal.ZERO;
        }
        return totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
    }
}
