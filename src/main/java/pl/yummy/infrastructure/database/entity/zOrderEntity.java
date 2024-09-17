package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class zOrderEntity {

    private Integer orderId;
    private zCustomerEntity customer;
    private zRestaurantEntity restaurant;
    private OffsetDateTime orderDateTime;
    private String orderStatus;
    private BigDecimal totalAmount;
    private zAddressEntity address;
    private String orderDescription;
    private String paymentMethod;
    private String paymentStatus;
    private OffsetDateTime estimatedDeliveryTime;
    private OffsetDateTime deliveryDateTime;
    private Boolean invoiceRequired;
    private zCourierEntity courier;


    private zOrderItemEntity orderItem;

    private zDeliveryEntity delivery;


}
