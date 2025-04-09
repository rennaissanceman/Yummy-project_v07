package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.OrderItemDTO;
import pl.yummy.domain.OrdersItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends OffsetDateTimeMapper{

    @Mapping(source = "menuItem.menuItemId", target = "menuItemId", defaultValue = "0L")
    OrderItemDTO toDTO(OrdersItem ordersItem);
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Dzięki temu mapper jest rejestrowany jako bean Springa.

    - @Mapping(source = "menuItem.menuItemId", target = "menuItemId") – Wyodrębnia identyfikator menuItem z obiektu
    OrdersItem i przypisuje go do pola menuItemId w DTO.

    - Pozostałe pola (ordersItemId, itemName, quantity, unitPrice, totalPrice, itemNotes) są mapowane automatycznie,
    gdyż ich nazwy i typy są zgodne między domeną a DTO.


    Takie rozwiązanie jest wystarczające, gdyż nie występują żadne złożone transformacje ani dodatkowe przypadki
    wymagające zabezpieczenia (np. obsługa null).

    Tutaj, jeśli menuItem lub menuItem.menuItemId byłoby null, pole menuItemId otrzyma wartość domyślną
    (tutaj przykładowo 0). Możesz dostosować tę logikę według własnych wymagań.

    W większości przypadków jednak, gdy dane są spójne i menuItem zawsze jest dostępne,
    dodatkowe rozwiązania nie są konieczne.
    */
