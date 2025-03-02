package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.AvailableDeliveryAreaDTO;
import pl.yummy.domain.AvailableDeliveryArea;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface AvailableDeliveryAreaMapper {

    @Mapping(source = "restaurant.restaurantId", target = "restaurantId")
    AvailableDeliveryAreaDTO toDTO(final AvailableDeliveryArea availableDeliveryArea);
}

    /*
    Wyjaśnienie:
    - Adnotacja @Mapper(componentModel = "spring", uses = {AddressMapper.class})
    Mapper jest zarządzany przez Springa, a parametr uses umożliwia wykorzystanie innego mappera (AddressMapper)
    do mapowania zagnieżdżonego obiektu Address na AddressDTO.

    - Adnotacja @Mapping(source = "restaurant.restaurantId", target = "restaurantId")
    Tutaj mapujemy pole restaurant.restaurantId z obiektu domenowego na pole restaurantId w DTO. Dzięki temu możemy
    uzyskać tylko identyfikator restauracji, bez całego obiektu Restaurant, co odpowiada uproszczonej reprezentacji w DTO.

    - Prosta metoda mapująca
    Metoda toDTO przekształca cały obiekt AvailableDeliveryArea na AvailableDeliveryAreaDTO. Reszta pól,
    takich jak availableDeliveryAreaId czy address, jest odwzorowywana automatycznie (o ile nazwy są zgodne).



    Jeśli w przyszłości zechcesz rozszerzyć DTO o dodatkowe informacje (np. liczbę zamówień czy dostaw),
    możesz dodać kolejne metody lub mapowania niestandardowe, podobnie jak to robiono w bardziej złożonych mapperach.
    */