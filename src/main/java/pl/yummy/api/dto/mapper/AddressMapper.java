package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.AddressDTO;
import pl.yummy.domain.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {


    // Mapowanie z domeny do DTO - tylko podstawowe pola są przekazywane,
    // dodatkowe pola (restaurant, availableDeliveryArea, deliveryAddress, billingInformation)
    // nie są uwzględniane, ponieważ nie występują w DTO.
    AddressDTO toDTO(final Address address);

    // Mapowanie z DTO do domeny.
    // Dla pól, które występują w domenie, ale nie w DTO, używamy adnotacji ignore,
    // aby uniknąć błędów podczas mapowania.
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "availableDeliveryArea", ignore = true)
    @Mapping(target = "deliveryAddress", ignore = true)
    @Mapping(target = "billingInformation", ignore = true)
    Address toDomain(final AddressDTO addressDTO);
}

    /*
    Wyjaśnienie:
    - Adnotacja @Mapper(componentModel = "spring"):
    Umożliwia automatyczne generowanie implementacji mappera i rejestrację jej jako beana w kontekście Springa.

    - Metoda toDTO:
    Mapuje obiekt typu Address na AddressDTO. MapStruct automatycznie odwzorowuje pola o tych samych nazwach.

    - Metoda toDomain:
    Mapuje obiekt typu AddressDTO na Address. Pola, które nie występują w DTO, a istnieją w modelu domenowym
    (tj. restaurant, availableDeliveryArea, deliveryAddress, billingInformation), są ignorowane za pomocą
    adnotacji @Mapping(..., ignore = true).



    Dzięki temu mapperowi możesz łatwo konwertować między obiektami domenowymi a obiektami DTO w swoim projekcie.
    */