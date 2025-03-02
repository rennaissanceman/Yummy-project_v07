package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.RequestOrderProcessingDTO;
import pl.yummy.domain.RequestOrderProcessing;

@Mapper(componentModel = "spring")
public interface RequestOrderProcessingMapper {

    // Mapowanie z DTO do domeny – pola, których nie ma w DTO, są ignorowane
    @Mapping(target = "desiredDeliveryStatus", ignore = true)
    @Mapping(target = "desiredPaymentStatus", ignore = true)
    @Mapping(target = "processingStartTime", ignore = true)
    @Mapping(target = "processingNotes", ignore = true)
    RequestOrderProcessing toDomain(RequestOrderProcessingDTO dto);

    // Mapowanie z domeny do DTO – tylko pola wspólne są odwzorowywane
    RequestOrderProcessingDTO toDTO(RequestOrderProcessing request);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring")
    Mapper jest rejestrowany jako bean Springa, co ułatwia jego wstrzykiwanie.

    - toDomain(RequestOrderProcessingDTO dto)
    Podczas mapowania z DTO na obiekt domenowy ignorujemy pola, które nie występują w DTO (desiredDeliveryStatus,
    desiredPaymentStatus, processingStartTime, processingNotes). W wyniku tych pól zostaną ustawione jako null lub domyślne wartości.

    - toDTO(RequestOrderProcessing request)
    Metoda odwzorowuje wszystkie wspólne pola (courierIdentifier, orderNumber, orderItemIdentifier, orderItemQuantity,
    processingCode, processingTime, comment, done).


    Takie rozwiązanie jest wystarczające dla prostych przypadków, gdy struktura DTO odpowiada strukturze domenowej,
    a dodatkowe pola w domenie są pomijane przy odwrotnej konwersji.
    */