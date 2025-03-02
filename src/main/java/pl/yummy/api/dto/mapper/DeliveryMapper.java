package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.DeliveryDTO;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "mapDeliveryStatus")
    DeliveryDTO toDTO(Delivery delivery);

    @Named("mapDeliveryStatus")
    default String mapDeliveryStatus(DeliveryStatusEnumDomain deliveryStatus) {
        return deliveryStatus == null ? null : deliveryStatus.name();
    }
    /*
    // Opcjonalnie: jeśli w przyszłości będziesz chciał mapować dodatkowe, wyliczane pola (np. isLate),
    // możesz dodać metodę domyślną, np.:
    // default Boolean mapIsLate(Delivery delivery) {
    //     return delivery.isLate();
    // }

     */
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co umożliwia wstrzykiwanie
    go w innych komponentach.

    - @Mapping z qualifiedByName – Używamy kwalifikowanej metody mapDeliveryStatus do konwersji pola deliveryStatus
    z typu DeliveryStatusEnumDomain na String.

    - Metoda domyślna mapDeliveryStatus – Sprawdza, czy przekazany enum jest null, i jeśli nie, zwraca jego nazwę
    (przy pomocy metody name()).

    - Opcjonalnie – Możesz dodać metody do mapowania dodatkowych, wyliczanych pól (np. isLate) w przyszłości,
    jeśli DTO będzie ich wymagać.


    Obecna implementacja spełnia wymagania, ponieważ poza konwersją deliveryStatus, pozostałe pola mają zgodne typy
    i nie wymagają specjalnej obsługi.
    */
