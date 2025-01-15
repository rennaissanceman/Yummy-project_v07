package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;


@Value
@Builder
public class ToDoDeliveryTracking {

//    Cel: Śledzenie statusu dostawy w czasie rzeczywistym.

//    Dzięki takiej klasie można lepiej zarządzać stanami dostawy i aktualizować je w interfejsie użytkownika.



    String orderId;
    String courierId;
    String courierStatus; // Np. "PICKED_UP", "ON_THE_WAY", "DELIVERED"
    OffsetDateTime lastUpdatedTime;
    String currentLocation; // Opcjonalnie, GPS lub nazwa lokalizacji
}
