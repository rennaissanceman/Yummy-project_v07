package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;

public class OrderingServiceRequestEntity {

    private Integer orderingServiceRequestId;
    private String orderingNumber;
    private OffsetDateTime dateTime;
    private String status;
    private CustomerEntity customer;
    private RestaurantEntity restaurant;
    private CourierEntity courier;
    private AddressEntity address;
}
