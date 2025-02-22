package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourierDTO {

//    Do reprezentacji danych kuriera

    Long courierId;
    String courierNumber;
    String courierName;
    String courierSurname;
    String vehicleType;
    String vehicleRegistrationNumber;
    Double averageRatings;
    Integer deliveryCount;
    String courierStatus;
    OffsetTime hireDate;
}
