package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@With
@Value
@Builder
public class ViewMenuAnalysisViewDTO {

    /*
    Służy do analizy popularności poszczególnych pozycji w menu. Zawiera informacje o liczbie zamówień danej pozycji,
    wygenerowanym przychodzie oraz wskaźnikach popularności. Umożliwia ocenę, które pozycje cieszą się
    największym zainteresowaniem klientów.
    */

    Integer menuItemId;
    String itemName;
    Integer totalOrders;
    BigDecimal revenueGenerated;
    Boolean isPopular;

    public boolean checkPopularityThreshold(int threshold) {
        return totalOrders != null && totalOrders > threshold;
    }
}
