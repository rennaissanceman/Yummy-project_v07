package pl.yummy.domain.view;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class RestaurantOverview {

    String restaurantName;
    String address;      // sformatowany adres restauracji
    String phone;
    String email;
    String website;
    Double averageRating;
    Integer ratingCount;
    Integer totalOrders; // np. liczba zamówień – opcjonalnie
}


    /*
    Read Model / Value Object – zbiorcze dane o restauracji.

    RestaurantOverview.
    Model, który zbiera najważniejsze dane o restauracji – takie jak ocena, liczba zamówień, dostępność menu,
    adres oraz dane kontaktowe – użyteczny na potrzeby prezentacji informacji na stronie głównej aplikacji
    lub w panelu właściciela.
    */