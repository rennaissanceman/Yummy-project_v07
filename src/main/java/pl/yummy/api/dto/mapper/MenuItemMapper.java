package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.MenuItemDTO;
import pl.yummy.domain.MenuItem;
import pl.yummy.domain.enums.DietTypeEnumDomain;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    @Mapping(source = "menu.menuId", target = "menuId")
    @Mapping(source = "dietType", target = "dietType", qualifiedByName = "mapDietType")
    MenuItemDTO toDTO(MenuItem menuItem);

    @Named("mapDietType")
    default String mapDietType(DietTypeEnumDomain dietType) {
        return dietType == null ? null : dietType.name();
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest zarządzany przez Springa.

    - @Mapping(source = "menu.menuId", target = "menuId") – Wyodrębnia identyfikator menu z obiektu Menu (domenowego)
    i przypisuje go do pola menuId w DTO.

    - @Mapping(source = "dietType", target = "dietType", qualifiedByName = "mapDietType") – Konwertuje
    enum DietTypeEnumDomain na String, używając metody mapDietType.

    - Metoda mapDietType – Jeśli przekazany enum jest null, metoda zwraca null, w przeciwnym przypadku zwraca nazwę enuma.


    Dzięki tej implementacji uzyskujemy spójną konwersję obiektu MenuItem z domeny na uproszczoną reprezentację DTO.
    */