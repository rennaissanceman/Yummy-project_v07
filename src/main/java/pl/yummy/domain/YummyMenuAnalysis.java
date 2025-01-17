package pl.yummy.domain;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = "menuItemId")
@ToString(of = {"menuItemId", "itemName", "totalOrders", "revenueGenerated", "isPopular"})
public class YummyMenuAnalysis {

    // Analizuje popularność pozycji menu w restauracji.

    Integer menuItemId; // ID pozycji w menu
    String itemName; // Nazwa pozycji
    Integer totalOrders; // Liczba zamówień tej pozycji
    BigDecimal revenueGenerated; // Przychód wygenerowany przez tę pozycję
    Boolean isPopular; // Czy pozycja jest popularna

    public boolean checkPopularityThreshold(int threshold) {
        return totalOrders != null && totalOrders > threshold;
    }
}
