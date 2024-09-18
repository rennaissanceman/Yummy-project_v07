package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class DeliveryEntity {

    private Integer deliveryId;
    private String deliveryNumber;
    private OrderEntity order;
    private CourierEntity courier;
    private OffsetDateTime deliveryStartDateTime;
    private OffsetDateTime estimatedDeliveryTime;
    private OffsetDateTime actualDeliveryDateTime;
    private String deliveryStatus;
    private String deliveryNotes;
    private BigDecimal deliveryFee;
}
