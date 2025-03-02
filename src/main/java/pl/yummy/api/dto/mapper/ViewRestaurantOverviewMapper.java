package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.ViewRestaurantOverviewDTO;
import pl.yummy.domain.ViewRestaurantOverview;

@Mapper(componentModel = "spring")
public interface ViewRestaurantOverviewMapper {

    ViewRestaurantOverviewDTO toDTO(ViewRestaurantOverview overview);

    ViewRestaurantOverview toDomain(ViewRestaurantOverviewDTO dto);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co umożliwia jego łatwe
    wstrzykiwanie w innych komponentach.

    - toDTO/toDomain – Standardowe metody mapujące między obiektem domenowym a DTO.

    - Ponieważ wszystkie pola mają te same nazwy i typy, MapStruct odwzorowuje je automatycznie bez dodatkowych adnotacji.
    */