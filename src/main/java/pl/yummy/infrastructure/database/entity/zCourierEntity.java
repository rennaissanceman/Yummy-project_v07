package pl.yummy.infrastructure.database.entity;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

public class zCourierEntity {

    private Integer courierId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String vehicleType;
    private String vehicleRegistrationNumber;
    private Double averageRatings;
    private Integer deliveryCount;
    private Boolean isActive;
    private OffsetTime hireDate;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;


    private zDeliveryEntity delivery;
}
