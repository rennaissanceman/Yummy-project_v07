package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;

public class zDeliveryAreaEntity {

    private Integer deliveryAreaId;
    private zRestaurantEntity restaurant;
    private zAvailableStreetEntity availableStreet;
    private BigDecimal deliveryFee;
    private BigDecimal minOrderAmount;
    private Boolean isActive;
}
