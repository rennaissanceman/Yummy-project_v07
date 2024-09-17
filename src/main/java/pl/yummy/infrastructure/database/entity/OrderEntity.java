package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class OrderEntity {

    private Integer orderId;
    private CustomerEntity customer;
    private RestaurantEntity restaurant;
    private OffsetDateTime orderDateTime;
    private String orderStatus;
    private BigDecimal totalAmount;
    private AddressEntity address;
    private String orderDescription;
    private String paymentMethod;
    private String paymentStatus;
    private OffsetDateTime estimatedDeliveryTime;
    private OffsetDateTime deliveryDateTime;
    private Boolean invoiceRequired;
    private CourierEntity courier;


    private OrderItemEntity orderItem;

    private DeliveryEntity delivery;


}
