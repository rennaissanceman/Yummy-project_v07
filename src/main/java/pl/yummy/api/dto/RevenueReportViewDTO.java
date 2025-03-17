package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@With
@Value
@Builder
public class RevenueReportViewDTO {

    /*
     Model raportu finansowego – zawiera dane dotyczące przychodów oraz statystyk zamówień.
     Umożliwia wygenerowanie raportu finansowego za określony okres. Zawiera informacje o łącznym przychodzie,
     liczbie zamówień oraz średniej wartości zamówienia. Taki widok jest przydatny do analiz finansowych
     i prezentacji wyników działalności.
     */
    Long reportId;
    OffsetDateTime startDate;
    OffsetDateTime endDate;
    BigDecimal totalRevenue;
    Integer totalOrders;
    BigDecimal averageOrderValue;

    public BigDecimal calculateAverageOrderValue() {
        if (totalOrders == null || totalOrders == 0) {
            return BigDecimal.ZERO;
        }
        return totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
    }
}
