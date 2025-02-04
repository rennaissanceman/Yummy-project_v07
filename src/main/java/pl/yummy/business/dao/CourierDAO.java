package pl.yummy.business.dao;

import pl.yummy.domain.Courier;
import pl.yummy.domain.enums.CourierStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface CourierDAO {

    // Find a courier by their unique courier number
    Optional<Courier> findByCourierNumber(String courierNumber);

    // Find all couriers by their status
    List<Courier> findByCourierStatus(CourierStatusEnumDomain courierStatus);

    // Find couriers with average ratings greater than or equal to a specified value
    List<Courier> findByAverageRatingsGreaterThanEqual(Double minimumRating);

    // Find couriers hired after a specific date
    List<Courier> findByHireDateAfter(OffsetDateTime hireDate);

    // Find couriers by vehicle type
    List<Courier> findByVehicleType(String vehicleType);

}
