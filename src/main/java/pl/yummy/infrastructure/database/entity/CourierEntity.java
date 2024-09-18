package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

public class CourierEntity {

    private Integer courierId;
    private String courierNumber;
    private ContactDetailsEntity contactDetails;
    private UserLogDataEntity userLogData;
    private String vehicleType;
    private String vehicleRegistrationNumber;
    private Double averageRatings;
    private Integer deliveryCount;
    private OffsetTime hireDate;

}
