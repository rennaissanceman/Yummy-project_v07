package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;

public class DeliveryAreaEntity {

    private Integer deliveryAreaId;
    private RestaurantEntity restaurant;
    private AvailableStreetEntity availableStreet;
    private BigDecimal deliveryFee;
    private BigDecimal minOrderAmount;
    private Boolean isActive;
}
