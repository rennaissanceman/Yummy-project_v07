package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class DeliveryStatusOverviewViewDTO {

    /*
    Przedstawia statystyki dotyczące dostaw w określonym obszarze. Zawiera dane o łącznej liczbie dostaw,
    liczbie dostaw opóźnionych, udanych oraz tych, które są w trakcie realizacji. Pozwala szybko ocenić
    efektywność procesu dostawy i identyfikować obszary wymagające poprawy.
    */

    Long deliveryAreaId;     // ID obszaru dostawy
    String deliveryAreaName;    // Nazwa obszaru dostawy
    Integer totalDeliveries;    // Łączna liczba dostaw
    Double averageDeliveryTime; // Średni czas dostawy (w minutach)
    Integer lateDeliveries;     // Liczba dostaw opóźnionych
    Integer successfulDeliveries; // Liczba dostaw zakończonych sukcesem
    Integer inTransit;

    public double calculateLateDeliveryRate() {
        if (totalDeliveries == null || totalDeliveries == 0) {
            return 0.0;
        }
        return (double) lateDeliveries / totalDeliveries * 100.0;
    }
}
