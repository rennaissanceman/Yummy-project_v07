package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class DeliveryStatusOverviewView {

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

    /*
    Read Model / Value Object – przegląd statusów dostaw.
    Przegląd statusów dostaw, adaptowany z YummyDeliveryPerformance

    DeliveryStatusOverview.
    Wartościowy obiekt, który agreguje dane dotyczące dostaw – na przykład liczbę dostaw w różnych statusach
    (np. opóźnione, dostarczone na czas), co może służyć do generowania raportów lub dashboardu dla menedżera dostaw.
    */