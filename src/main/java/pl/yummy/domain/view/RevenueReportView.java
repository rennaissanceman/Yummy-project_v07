package pl.yummy.domain.view;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

@With
@Value
@Builder
public class RevenueReportView {

    // Tworzy raport finansowy dla danego okresu, restauracji lub całego systemu.
    //  Raport finansowy – adaptacja YummyRevenueReport

    Integer reportId; // ID raportu
    OffsetDateTime startDate; // Data rozpoczęcia raportu
    OffsetDateTime endDate; // Data zakończenia raportu
    BigDecimal totalRevenue; // Łączny przychód
    Integer totalOrders; // Łączna liczba zamówień
    BigDecimal averageOrderValue; // Średnia wartość zamówienia


    public BigDecimal calculateAverageOrderValue() {
        if (totalOrders == null || totalOrders == 0) {
            return BigDecimal.ZERO;
        }
        return totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
    }
}
