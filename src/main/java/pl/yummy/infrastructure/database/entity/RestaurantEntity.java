package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class RestaurantEntity {

    private Integer restaurantId;
    private String restaurantName;
    private String description;
    private AddressEntity address;
    private OwnerEntity owner;
    private String phoneNumber;
    private String email;
    private String website;
    private String openingHours;
    private String cuisineType;
    private Double averageRating;
    private Integer ratingCount;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;


    private OrderEntity order;
    private MenuItemEntity menuItem;

}
