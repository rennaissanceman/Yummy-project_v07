package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class RestaurantOverviewViewDTO {

    /*
     Podstawowy model widoku restauracji – zawiera dane kontaktowe i statystyki.
     Prezentuje podstawowe dane restauracji, takie jak nazwa, sformatowany adres, dane kontaktowe
     oraz kluczowe statystyki (np. średnia ocena, liczba zamówień).
     */
    String restaurantName;
    String address;      // sformatowany adres restauracji
    String phone;
    String email;
    String website;
    Double averageRating;
    Integer ratingCount;
    Integer totalOrders; // np. liczba zamówień – opcjonalnie
}
