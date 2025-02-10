package pl.yummy.domain.view;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class DeliveryStatusOverview {

    Integer deliveryAreaId;
    String deliveryAreaName;
    Integer totalDeliveries;
    Integer lateDeliveries;
    Integer successfulDeliveries;
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