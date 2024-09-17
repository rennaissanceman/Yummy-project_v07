package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class zDeliveryEntity {

    private Integer deliveryId;
    private zOrderEntity order;
    private zCustomerEntity customer;
    private OffsetDateTime deliveryStartDateTime;
    private OffsetDateTime estimatedDeliveryTime;
    private OffsetDateTime actualDeliveryDateTime;
    private String deliveryStatus;
    private zCourierEntity courier;
    private String deliveryNotes;
    private BigDecimal deliveryFee;
}
