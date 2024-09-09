package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;

public class DeliveryArea {

    private Integer deliveryAreaId;
    private RestaurantEntity restaurant;
    private AvailableStreet availableStreet;
    private BigDecimal deliveryFee;
    private BigDecimal minOrderAmount;
    private Boolean isActive;
}
