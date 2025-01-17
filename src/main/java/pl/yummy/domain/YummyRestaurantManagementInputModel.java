package pl.yummy.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "ownerId", "restaurantDetails", "menuItems", "deliveryAddresses", "customerReviews", "openingHours"})
public class YummyRestaurantManagementInputModel {

    // zarzadzanie restauracja przez właściciela

    Integer restaurantId; // ID restauracji
    Integer ownerId; // ID właściciela restauracji
    Integer newOwnerId; // ID nowego właściciela (jeśli następuje zmiana)
    RestaurantDetailsInput restaurantDetails; // Szczegóły restauracji do aktualizacji
    List<MenuItemInput> menuItems; // Lista pozycji menu do zarządzania
    List<DeliveryAddressInput> deliveryAddresses; // Lista adresów dostaw do zarządzania
    List<CustomerReviewInput> customerReviews; // Opinie klientów do zarządzania
    OpeningHoursInput openingHours; // Godziny otwarcia restauracji

    /**
     * Waliduje dane wejściowe dla zarządzania restauracją.
     *
     * @return true, jeśli dane są poprawne.
     */
    public boolean isValid() {
        return restaurantId != null
                && ownerId != null
                && (newOwnerId == null || newOwnerId > 0)
                && (restaurantDetails == null || restaurantDetails.isValid())
                && (menuItems != null && menuItems.stream().allMatch(MenuItemInput::isValid))
                && (deliveryAddresses != null && deliveryAddresses.stream().allMatch(DeliveryAddressInput::isValid))
                && (customerReviews == null || customerReviews.stream().allMatch(CustomerReviewInput::isValid))
                && (openingHours == null || openingHours.isValid());
    }

    /**
     * Klasa wewnętrzna reprezentująca szczegóły restauracji.
     */
    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "restaurantId")
    @ToString(of = {"restaurantId", "restaurantName", "phone", "email", "website", "cuisineType"})
    public static class RestaurantDetailsInput {
        Integer restaurantId;
        String restaurantName; // Nazwa restauracji
        String phone; // Telefon restauracji
        String email; // Email restauracji
        String website; // Strona internetowa
        String cuisineType; // Typ kuchni (np. ITALIAN, CHINESE)

        public boolean isValid() {
            return restaurantName != null && !restaurantName.isBlank()
                    && phone != null && !phone.isBlank()
                    && email != null && !email.isBlank()
                    && cuisineType != null && !cuisineType.isBlank();
        }
    }

    /**
     * Klasa wewnętrzna reprezentująca dane pozycji menu.
     */
    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "menuItemId")
    @ToString(of = {"menuItemId", "itemName", "price", "isAvailable", "dietType", "ingredients", "displayOrder"})
    public static class MenuItemInput {
        Integer menuItemId; // ID pozycji menu (null, jeśli dodajemy nową)
        String itemName; // Nazwa pozycji w menu
        BigDecimal price; // Cena pozycji
        Boolean isAvailable; // Czy pozycja jest dostępna
        String dietType; // Typ diety (np. VEGETARIAN, VEGAN)
        String ingredients; // Składniki (opis)
        Integer displayOrder; // Kolejność wyświetlania w menu

        public boolean isValid() {
            return itemName != null && !itemName.isBlank()
                    && price != null && price.compareTo(BigDecimal.ZERO) > 0
                    && isAvailable != null
                    && displayOrder != null && displayOrder > 0;
        }
    }

    /**
     * Klasa wewnętrzna reprezentująca dane adresów dostaw.
     */
    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "addressId")
    @ToString(of = {"addressId", "country", "city", "postalCode", "street"})
    public static class DeliveryAddressInput {
        Integer addressId; // ID adresu (null, jeśli dodajemy nowy)
        String country; // Kraj
        String city; // Miasto
        String postalCode; // Kod pocztowy
        String street; // Ulica

        public boolean isValid() {
            return country != null && !country.isBlank()
                    && city != null && !city.isBlank()
                    && postalCode != null && !postalCode.isBlank()
                    && street != null && !street.isBlank();
        }
    }

    /**
     * Klasa wewnętrzna reprezentująca opinie klientów.
     */
    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "reviewId")
    @ToString(of = {"reviewId", "customerId", "rating", "comments"})
    public static class CustomerReviewInput {
        Integer reviewId; // ID opinii (null, jeśli to nowa opinia)
        Integer customerId; // ID klienta
        Integer restaurantId; // ID restauracji
        Integer rating; // Ocena (np. od 1 do 5)
        String comments; // Komentarz klienta

        public boolean isValid() {
            return rating != null && rating >= 1 && rating <= 5
                    && restaurantId != null
                    && customerId != null;
        }
    }

    /**
     * Klasa wewnętrzna reprezentująca godziny otwarcia restauracji.
     */
    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "restaurantId")
    @ToString(of = {"restaurantId", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"})
    public static class OpeningHoursInput {

        Integer restaurantId;
        LocalTime monday; // Godziny otwarcia w poniedziałek
        LocalTime tuesday; // Godziny otwarcia we wtorek
        LocalTime wednesday; // Godziny otwarcia w środę
        LocalTime thursday; // Godziny otwarcia w czwartek
        LocalTime friday; // Godziny otwarcia w piątek
        LocalTime saturday; // Godziny otwarcia w sobotę
        LocalTime sunday; // Godziny otwarcia w niedzielę

        public boolean isValid() {
            return monday != null
                    && tuesday != null
                    && wednesday != null
                    && thursday != null
                    && friday != null
                    && saturday != null
                    && sunday != null;
        }
    }
}
