package pl.yummy.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "deliveryAreaId")
@ToString(of = {"deliveryAreaId", "totalDeliveries", "averageDeliveryTime", "lateDeliveries", "successfulDeliveries"})
public class YummyDeliveryPerformance {

    // Reprezentuje statystyki i wydajność dostaw w aplikacji.

    Integer deliveryAreaId; // ID obszaru dostawy
    String deliveryAreaName; // Nazwa obszaru dostawy
    Integer totalDeliveries; // Łączna liczba dostaw
    Double averageDeliveryTime; // Średni czas dostawy w minutach
    Integer lateDeliveries; // Liczba dostaw opóźnionych
    Integer successfulDeliveries; // Liczba dostaw zakończonych sukcesem

    public double calculateLateDeliveryRate() {
        if (totalDeliveries == null || totalDeliveries == 0) {
            return 0.0;
        }
        return (double) lateDeliveries / totalDeliveries * 100.0;
    }
}
