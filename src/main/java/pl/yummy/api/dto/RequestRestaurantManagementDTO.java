package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@With
@Value
@Builder
public class RequestRestaurantManagementDTO {

    Integer restaurantId;
    Integer ownerId;
    Integer newOwnerId;
    RestaurantDetailsDTO restaurantDetails;
    List<MenuItemDTO> menuItems;
    List<DeliveryAddressDTO> deliveryAddresses;
    List<CustomerReviewDTO> customerReviews;
    OpeningHoursDTO openingHours;

    @With
    @Value
    @Builder
    public static class RestaurantDetailsDTO {
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
    public static class MenuItemDTO {
        Integer menuItemId;      // Jeśli null, oznacza nową pozycję
        String itemName;
        BigDecimal price;
        Boolean isAvailable;
        String dietType;
        String ingredients;
        Integer displayOrder;
    }

    @With
    @Value
    @Builder
    public static class DeliveryAddressDTO {
        Integer addressId;       // Jeśli null, nowy adres
        String country;
        String city;
        String postalCode;
        String street;
    }

    @With
    @Value
    @Builder
    public static class CustomerReviewDTO {
        Integer reviewId;        // Jeśli null, nowa opinia
        Integer customerId;
        Integer restaurantId;
        Integer rating;
        String comments;
    }

    @With
    @Value
    @Builder
    public static class OpeningHoursDTO {
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
