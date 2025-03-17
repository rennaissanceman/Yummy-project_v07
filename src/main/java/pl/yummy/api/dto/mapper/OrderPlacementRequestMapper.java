package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.OrderPlacementRequestDTO;
import pl.yummy.domain.OrderPlacementRequest;

@Mapper(componentModel = "spring")
public interface OrderPlacementRequestMapper {

    OrderPlacementRequestDTO toDTO(OrderPlacementRequest orderPlacementRequest);

    OrderPlacementRequest toDomain(OrderPlacementRequestDTO orderPlacementRequestDTO);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co ułatwia jego wstrzykiwanie
    w innych komponentach.

    - Metody toDTO i toDomain konwertują obiekty między warstwą domenową a DTO.


    Ponieważ wszystkie pola mają te same nazwy i typy, MapStruct automatycznie odwzorowuje właściwości,
    bez potrzeby stosowania dodatkowych adnotacji @Mapping.
    */