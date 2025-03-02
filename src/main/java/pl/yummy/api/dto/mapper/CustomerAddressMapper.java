package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.CustomerAddressDTO;
import pl.yummy.domain.CustomerAddress;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface CustomerAddressMapper {

    @Mapping(source = "customerAddressId", target = "customerAddressId")
    @Mapping(source = "isDefault", target = "isDefault")
    @Mapping(source = "address", target = "address")
    // Mapowanie dostępnego obszaru dostawy – wykorzystujemy metodę domyślną zabezpieczającą przed null
    @Mapping(source = "this", target = "availableDeliveryAreaId", qualifiedByName = "mapAvailableDeliveryAreaId")
    CustomerAddressDTO toDTO(final CustomerAddress customerAddress);

    /*
     * Metoda domyślna mapująca obiekt CustomerAddress na identyfikator availableDeliveryArea.
     * Jeśli availableDeliveryArea jest null, zwraca null.
     */
    @Named("mapAvailableDeliveryAreaId")
    default Long mapAvailableDeliveryAreaId(CustomerAddress customerAddress) {
        return customerAddress.getAvailableDeliveryArea() != null
                ? customerAddress.getAvailableDeliveryArea().getAvailableDeliveryAreaId()
                : null;
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring", uses = {AddressMapper.class})
    Dzięki temu mapper jest rejestrowany jako bean Springa, a zagnieżdżone mapowanie obiektu Address odbywa się
    przy pomocy AddressMappera.

    - @Mapping(source = "this", target = "availableDeliveryAreaId", qualifiedByName = "mapAvailableDeliveryAreaId")
    Zamiast bezpośrednio odwoływać się do pola (np. "availableDeliveryArea.availableDeliveryAreaId"),
    zastosowano mapowanie na całym obiekcie (this). Metoda domyślna o nazwie mapAvailableDeliveryAreaId sprawdza,
    czy obiekt availableDeliveryArea istnieje – jeśli tak, zwraca jego identyfikator, a w przeciwnym razie null.

    - Reszta mapowań
    Pola customerAddressId, isDefault oraz address są mapowane standardowo – przy czym Address zostanie przekonwertowany
     przez AddressMapper.


    Takie podejście zwiększa odporność mapowania na null oraz ujednolica logikę mapowania zagnieżdżonych obiektów.
*/
