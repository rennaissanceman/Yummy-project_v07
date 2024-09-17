package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class DeliveryEntity {

    private Integer deliveryId;
    private OrderEntity order;
    private CustomerEntity customer;
    private OffsetDateTime deliveryStartDateTime;
    private OffsetDateTime estimatedDeliveryTime;
    private OffsetDateTime actualDeliveryDateTime;
    private String deliveryStatus;
    private CourierEntity courier;
    private String deliveryNotes;
    private BigDecimal deliveryFee;
}
