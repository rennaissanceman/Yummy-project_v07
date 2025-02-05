package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.CourierStatusEnumDomain;

import java.time.OffsetTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "courierId")
@ToString(of = {"courierId", "courierNumber", "courierName", "courierSurname", "userAuth", "vehicleType",
        "vehicleRegistrationNumber", "averageRatings", "deliveryCount", "hireDate"})
public class Courier {

    Long courierId;
    String courierNumber;
    String courierName;
    String courierSurname;
    UserAuth userAuth;
    String vehicleType;
    String vehicleRegistrationNumber;
    Double averageRatings;
    Integer deliveryCount;
    CourierStatusEnumDomain courierStatus;
    OffsetTime hireDate;
    Set<Delivery> deliveries;

    public boolean isActiveCourier() {
        return courierStatus == CourierStatusEnumDomain.AVAILABLE;
    }
}
