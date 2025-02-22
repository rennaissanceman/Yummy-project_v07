package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

//    Kluczowe dane restauracji
    Long restaurantId;
    String restaurantName;
    OwnerDTO owner; //(lub tylko Long ownerId)
    AddressDTO address;
    String phone;
    String email;
    String website;
    String openingHours;
    String cuisineType;
    Double averageRating;
    Integer ratingCount;
    String description;
    String logoURL;
}
