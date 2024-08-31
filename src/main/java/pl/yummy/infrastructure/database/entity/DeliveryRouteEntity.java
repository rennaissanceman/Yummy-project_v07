package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;

public class DeliveryRouteEntity {

    private Integer deliveryRouteId;
    private OrderingServiceRequestEntity orderingServiceRequest;
    private CourierEntity courier;
    private OffsetDateTime departureTime;
    private OffsetDateTime deliveryTime;
    private String status;
}
