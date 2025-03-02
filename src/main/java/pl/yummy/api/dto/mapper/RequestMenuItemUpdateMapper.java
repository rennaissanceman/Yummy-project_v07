package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.RequestMenuItemUpdateDTO;
import pl.yummy.domain.RequestMenuItemUpdate;

@Mapper(componentModel = "spring")
public interface RequestMenuItemUpdateMapper {

    RequestMenuItemUpdateDTO toDTO(RequestMenuItemUpdate requestMenuItemUpdate);

    RequestMenuItemUpdate toDomain(RequestMenuItemUpdateDTO requestMenuItemUpdateDTO);
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