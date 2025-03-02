package pl.yummy.api.dto.mapper;

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

@Mapper(componentModel = "spring", uses = {MenuItemMapper.class})
public interface MenuMapper {

    @Mapping(source = "restaurant.restaurantId", target = "restaurantId")
    @Mapping(source = "menuItems", target = "menuItems", qualifiedByName = "mapMenuItems")
    MenuDTO toDTO(Menu menu);

    /*
     * Metoda domyślna mapująca zbiór menuItems na listę MenuItemDTO.
     * Jeżeli przekazany zbiór jest null, zwraca pustą listę.
     * Elementy są sortowane według menuItemId.
     */
    @Named("mapMenuItems")
    default List<MenuItemDTO> mapMenuItems(Set<MenuItem> menuItems) {
        if (menuItems == null) {
            return Collections.emptyList();
        }
        return menuItems.stream()
                .sorted(Comparator.comparing(MenuItem::getMenuItemId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /*
     * Metoda mapująca pojedynczy obiekt MenuItem na MenuItemDTO.
     * Implementacja jest delegowana do MenuItemMapper (zgodnie z konfiguracją w 'uses').
     */
    MenuItemDTO toDTO(MenuItem menuItem);
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