package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.MenuItemDTO;
import pl.yummy.domain.MenuItem;
import pl.yummy.domain.enums.DietTypeEnumDomain;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface MenuItemMapper extends OffsetDateTimeMapper{

    @Mapping(source = "menu.menuId", target = "menuId")
    @Mapping(source = "dietType", target = "dietType", qualifiedByName = "mapDietType")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "mapOffsetDateTimeToString")

    MenuItemDTO toDTO(MenuItem menuItem);

    @Named("mapDietType")
    default String mapDietType(DietTypeEnumDomain dietType) {
        return dietType == null ? null : dietType.name();
    }

    @Named("mapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime date) {
        // Przykład: formatowanie daty
        return date != null ? date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null;
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