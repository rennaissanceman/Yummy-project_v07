package pl.yummy.infrastructure.database.entity;

public class RestaurantEntity {

    private Integer restaurantId;
    private String restaurantName;
    private String NIP;
    private OwnerEntity owner;
    private AddressEntity address;
    private String description;
}
