package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.RestaurantOverviewViewDTO;
import pl.yummy.domain.RestaurantOverviewView;

@Mapper(componentModel = "spring")
public interface RestaurantOverviewViewMapper {

    RestaurantOverviewViewDTO toDTO(RestaurantOverviewView overview);

    RestaurantOverviewView toDomain(RestaurantOverviewViewDTO dto);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co umożliwia jego łatwe
    wstrzykiwanie w innych komponentach.

    - toDTO/toDomain – Standardowe metody mapujące między obiektem domenowym a DTO.

    - Ponieważ wszystkie pola mają te same nazwy i typy, MapStruct odwzorowuje je automatycznie bez dodatkowych adnotacji.
    */