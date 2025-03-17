package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.MenuItemUpdateRequestDTO;
import pl.yummy.domain.MenuItemUpdateRequest;

@Mapper(componentModel = "spring")
public interface MenuItemUpdateRequestMapper {

    MenuItemUpdateRequestDTO toDTO(MenuItemUpdateRequest menuItemUpdateRequest);

    MenuItemUpdateRequest toDomain(MenuItemUpdateRequestDTO menuItemUpdateRequestDTO);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co umożliwia wstrzykiwanie
    go w innych komponentach.

    - Metoda toDTO konwertuje obiekt domenowy na DTO, natomiast toDomain przekształca DTO z powrotem
    do obiektu domenowego.


    Ponieważ pola w obu klasach mają te same nazwy i typy, MapStruct automatycznie wykonuje odwzorowanie,
    dlatego nie potrzeba dodatkowych adnotacji @Mapping.
    */