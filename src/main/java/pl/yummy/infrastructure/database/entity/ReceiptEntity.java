package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;

public class ReceiptEntity {

    private Integer receiptId;
    private String receiptNumber;
    private OffsetDateTime dateTime;
    private OrderingServiceRequestEntity orderingServiceRequest;
    private CustomerEntity customer;
}
