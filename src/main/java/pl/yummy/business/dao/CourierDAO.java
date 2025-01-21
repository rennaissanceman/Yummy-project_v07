package pl.yummy.business.dao;

import pl.yummy.domain.Courier;
import pl.yummy.domain.enums.CourierStatusEnumDomain;

import java.util.List;
import java.util.Optional;

public interface CourierDAO {

    List<Courier> findByCourierStatus(CourierStatusEnumDomain status);
    Optional<Courier> findByCourierNumber(String courierNumber);
    List<Courier> findByAverageRatingsGreaterThanEqual(Double minRating);

    List<Courier> findAvailableCouriers();
    List<Courier> findByVehicleType(String vehicleType);

    List<Courier> findByAverageRatingsGreaterThan(Double rating);


    List<Courier> findByStatus(CourierStatusEnumDomain status);


    List<Courier> findByRatingsAbove(Double rating);
}
