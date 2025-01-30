package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.domain.enums.CourierStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.CourierEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourierJpaRepository extends JpaRepository<CourierEntity, Long> {

    Optional<CourierEntity> findByCourierNumber(String courierNumber);

    List<CourierEntity> findByCourierStatus(CourierStatusEnumDomain courierStatus);

    List<CourierEntity> findByAverageRatingsGreaterThanEqual(Double minimumRating);

    List<CourierEntity> findByHireDateAfter(OffsetDateTime hireDate);

    List<CourierEntity> findByVehicleType(String vehicleType);

}
