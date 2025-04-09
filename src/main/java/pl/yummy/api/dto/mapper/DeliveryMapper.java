package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.DeliveryDTO;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface DeliveryMapper extends OffsetDateTimeMapper {


    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "mapDeliveryStatus")
    @Mapping(source = "starTime", target = "starTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "endTime", target = "endTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "estimatedDeliveryTime", target = "estimatedDeliveryTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "actualDeliveryDateTime", target = "actualDeliveryDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    DeliveryDTO toDTO(Delivery delivery);

/*    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "mapStringToDeliveryStatus")
    @Mapping(source = "starTime", target = "starTime", qualifiedByName = "mapStringToOffsetDateTime")
    @Mapping(source = "endTime", target = "endTime", qualifiedByName = "mapStringToOffsetDateTime")
    @Mapping(source = "estimatedDeliveryTime", target = "estimatedDeliveryTime", qualifiedByName = "mapStringToOffsetDateTime")
    @Mapping(source = "actualDeliveryDateTime", target = "actualDeliveryDateTime", qualifiedByName = "mapStringToOffsetDateTime")*/
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "availableDeliveryArea", ignore = true)
    @Mapping(target = "courier", ignore = true)
    Delivery toDomain(DeliveryDTO deliveryDTO);

    @Named("mapDeliveryStatus")
    default String mapDeliveryStatus(DeliveryStatusEnumDomain deliveryStatus) {
        return deliveryStatus == null ? null : deliveryStatus.name();
    }

    @Named("mapStringToDeliveryStatus")
    default DeliveryStatusEnumDomain mapStringToDeliveryStatus(String status) {
        return status == null ? null : DeliveryStatusEnumDomain.valueOf(status);
    }
}
    /*
    // Opcjonalnie: jeśli w przyszłości będziesz chciał mapować dodatkowe, wyliczane pola (np. isLate),
    // możesz dodać metodę domyślną, np.:
    // default Boolean mapIsLate(Delivery delivery) {
    //     return delivery.isLate();
    // }




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
