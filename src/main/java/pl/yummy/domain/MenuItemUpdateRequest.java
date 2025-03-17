package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;

@With
@Value
@Builder
public class MenuItemUpdateRequest {

    Long menuItemId;
    Boolean isAvailable;
    BigDecimal price;
    String description; // Opcjonalnie – nowy opis produktu
}


    /*
    Request/Command DTO – do aktualizacji pozycji w menu.

    MenuItemUpdateRequest.
    Służy do aktualizacji szczegółów pozycji w menu – na przykład zmiany ceny, dostępności czy opisu.

    Przykładowe pola:

    - menuItemId
    - isAvailable
    - price
    - description
    */
