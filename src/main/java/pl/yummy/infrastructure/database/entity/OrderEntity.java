package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class OrderEntity {

    private Integer orderId;
    private String orderNumber;
    private CustomerEntity customer;
    private RestaurantEntity restaurant;
    private OffsetDateTime orderDateTime;
    private String orderStatus;
    private String orderDescription;
    private BigDecimal totalAmount;
    private RestaurantAvailableStreetEntity restaurantAvailableStreet;
    private DeliveryAddressEntity deliveryAddress;
    private OffsetDateTime estimatedDeliveryTime;
    private OffsetDateTime deliveryTime;


}
