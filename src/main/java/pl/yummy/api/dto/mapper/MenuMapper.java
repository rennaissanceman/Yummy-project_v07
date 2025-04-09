package pl.yummy.api.dto.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.MenuDTO;
import pl.yummy.api.dto.MenuItemDTO;
import pl.yummy.domain.Menu;
import pl.yummy.domain.MenuItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class, MenuItemMapper.class})
public interface MenuMapper extends OffsetDateTimeMapper{

    @Mapping(source = "restaurant.restaurantId", target = "restaurantId")
    @Mapping(source = "validFrom", target = "validFrom", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "validTo", target = "validTo", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "mapOffsetDateTimeToString")
/*    @Mapping(source = "restaurant.restaurantId", target = "restaurantId")
    // Usunięte kwalifikatory, gdyż pola w DTO są tego samego typu co w domain (OffsetDateTime)
    @Mapping(source = "validFrom", target = "validFrom")
    @Mapping(source = "validTo", target = "validTo")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")*/
    MenuDTO toDTO(Menu menu);


    /*
     * Metoda domyślna mapująca zbiór menuItems na listę MenuItemDTO.
     * Jeżeli przekazany zbiór jest null, zwraca pustą listę.
     * Elementy są sortowane według menuItemId.
     */
/*    @Named("mapMenuItems")
    default List<MenuItemDTO> mapMenuItems(Set<MenuItem> menuItems, @Context MenuItemMapper menuItemMapper) {
        if (menuItems == null) {
            return Collections.emptyList();
        }
        return menuItems.stream()
                .sorted(Comparator.comparing(MenuItem::getMenuItemId))
                .map(menuItemMapper::toDTO) // jawne wywołanie metody z MenuItemMapper
                .collect(Collectors.toList());
    }*/

    @Named("mapMenuItems")
    default List<MenuItemDTO> mapMenuItems(Set<MenuItem> menuItems, @org.mapstruct.Context MenuItemMapper menuItemMapper) {
        if (menuItems == null) {
            return Collections.emptyList();
        }
        return menuItems.stream()
                .sorted(Comparator.comparing(MenuItem::getMenuItemId))
                .map(menuItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * Metoda mapująca pojedynczy obiekt MenuItem na MenuItemDTO.
     * Implementacja jest delegowana do MenuItemMapper (zgodnie z konfiguracją w 'uses').
     */
/*    @Mapping(target = "menuId", ignore = true)
    MenuItemDTO toDTO(MenuItem menuItem);*/
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring", uses = {MenuItemMapper.class})
    Mapper jest rejestrowany jako bean Springa, a dzięki atrybutowi uses do mapowania zagnieżdżonych elementów menuItems
    wykorzystywany jest MenuItemMapper.

    - Mapowanie restaurantId:
    Pole restaurant.restaurantId z domeny jest odwzorowywane na pole restaurantId w DTO.

    - Mapowanie kolekcji menuItems:
    Używamy metody domyślnej mapMenuItems, która:
    - Zwraca pustą listę, gdy przekazany zbiór jest null.
    - Sortuje elementy według menuItemId (przy założeniu, że metoda getMenuItemId() jest dostępna w klasie MenuItem).
    - Mapuje każdy element przy użyciu metody toDTO(MenuItem menuItem). Ta metoda jest delegowana do MenuItemMapper
    (zgodnie z konfiguracją uses).



    Taka implementacja zwiększa bezpieczeństwo (zapewnia nie-nullową listę) oraz spójność mapowania kolekcji zgodnie
    z podejściem stosowanym w innych mapperach.
    */