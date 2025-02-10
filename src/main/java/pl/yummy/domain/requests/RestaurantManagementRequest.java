package pl.yummy.domain.requests;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalTime;
import java.util.List;

@With
@Value
@Builder
public class RestaurantManagementRequest {

    //    DTO do zarządzania restauracją – uproszczone wejście, bez metod walidacyjnych

    Integer restaurantId;
    Integer ownerId;
    Integer newOwnerId; // Opcjonalnie – gdy następuje zmiana właściciela
    RestaurantDetailsInput restaurantDetails;
    List<MenuItemInput> menuItems;
    List<DeliveryAddressInput> deliveryAddresses;
    List<CustomerReviewInput> customerReviews;
    OpeningHoursInput openingHours;

    @With
    @Value
    @Builder
    public static class RestaurantDetailsInput {
        Integer restaurantId;
        String restaurantName;
        String phone;
        String email;
        String website;
        String cuisineType;
    }

    @With
    @Value
    @Builder
    public static class MenuItemInput {
        Integer menuItemId;
        String itemName;
        java.math.BigDecimal price;
        Boolean isAvailable;
        String dietType;
        String ingredients;
        Integer displayOrder;
    }

    @With
    @Value
    @Builder
    public static class DeliveryAddressInput {
        Integer addressId;
        String country;
        String city;
        String postalCode;
        String street;
    }

    @With
    @Value
    @Builder
    public static class CustomerReviewInput {
        Integer reviewId;
        Integer customerId;
        Integer restaurantId;
        Integer rating;
        String comments;
    }

    @With
    @Value
    @Builder
    public static class OpeningHoursInput {
        Integer restaurantId;
        LocalTime monday;
        LocalTime tuesday;
        LocalTime wednesday;
        LocalTime thursday;
        LocalTime friday;
        LocalTime saturday;
        LocalTime sunday;
    }
}
