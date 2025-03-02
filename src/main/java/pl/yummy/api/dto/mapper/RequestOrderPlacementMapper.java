package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.RequestOrderPlacementDTO;
import pl.yummy.domain.RequestOrderPlacement;

@Mapper(componentModel = "spring")
public interface RequestOrderPlacementMapper {

    RequestOrderPlacementDTO toDTO(RequestOrderPlacement requestOrderPlacement);

    RequestOrderPlacement toDomain(RequestOrderPlacementDTO requestOrderPlacementDTO);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co ułatwia jego wstrzykiwanie
    w innych komponentach.

    - Metody toDTO i toDomain konwertują obiekty między warstwą domenową a DTO.


    Ponieważ wszystkie pola mają te same nazwy i typy, MapStruct automatycznie odwzorowuje właściwości,
    bez potrzeby stosowania dodatkowych adnotacji @Mapping.
    */