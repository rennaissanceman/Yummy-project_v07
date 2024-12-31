package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "restaurantName", "owner", "address", "phone", "email", "website",
        "openingHours", "cuisineType", "averageRating", "ratingCount", "description", "logoURL"})
public class Restaurant {

    Integer restaurantId;
    String restaurantName;
    Owner owner;
    Address address;
    String phone;
    String email;
    String website;
    String openingHours;
    CuisineTypeEnumDomain cuisineType;
    Double averageRating;
    Integer ratingCount;
    String description;
    String logoURL;
    Set<AvailableDeliveryArea> availableDeliveryAreas;
    Set<Menu> menus;
}
