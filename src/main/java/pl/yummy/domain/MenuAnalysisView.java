package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@With
@Value
@Builder
public class MenuAnalysisView {

    // Analizuje popularność pozycji menu w restauracji.

    Integer menuItemId;     // ID pozycji w menu
    String itemName;        // Nazwa pozycji
    Integer totalOrders;    // Liczba zamówień tej pozycji
    BigDecimal revenueGenerated; // Przychód wygenerowany przez tę pozycję
    Boolean isPopular;      // Czy pozycja jest popularna

    public boolean checkPopularityThreshold(int threshold) {
        return totalOrders != null && totalOrders > threshold;
    }
}
