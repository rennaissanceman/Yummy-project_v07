package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@Builder
public class YummyDeliveryTracking {

    String orderId;
    String courierId;
    String courierStatus; // Np. "PICKED_UP", "ON_THE_WAY", "DELIVERED"
    OffsetDateTime lastUpdatedTime;
    String currentLocation; // Opcjonalnie, GPS lub nazwa lokalizacji

}
