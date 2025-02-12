package pl.yummy.domain.view;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class DeliveryPerformanceView {

    // Reprezentuje statystyki i wydajność dostaw w aplikacji.

    Integer deliveryAreaId;     // ID obszaru dostawy
    String deliveryAreaName;    // Nazwa obszaru dostawy
    Integer totalDeliveries;    // Łączna liczba dostaw
    Double averageDeliveryTime; // Średni czas dostawy (w minutach)
    Integer lateDeliveries;     // Liczba dostaw opóźnionych
    Integer successfulDeliveries; // Liczba dostaw zakończonych sukcesem

    public double calculateLateDeliveryRate() {
        if (totalDeliveries == null || totalDeliveries == 0) {
            return 0.0;
        }
        return (double) lateDeliveries / totalDeliveries * 100.0;
    }
}
