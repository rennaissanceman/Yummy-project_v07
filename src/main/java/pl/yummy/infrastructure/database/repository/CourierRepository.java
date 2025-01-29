package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.CourierDAO;
import pl.yummy.domain.Courier;
import pl.yummy.domain.enums.CourierStatusEnumDomain;
import pl.yummy.infrastructure.database.repository.jpa.CourierJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.CourierEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CourierRepository implements CourierDAO {

    private final CourierJpaRepository courierJpaRepository;
    private final CourierEntityMapper mapper;

    @Override
    public Optional<Courier> findByCourierNumber(String courierNumber) {
        return courierJpaRepository.findByCourierNumber(courierNumber)
                .map(mapper::mapFromEntity);
    }

    @Override
    public List<Courier> findByCourierStatus(CourierStatusEnumDomain courierStatus) {
        return courierJpaRepository.findByCourierStatus(courierStatus).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Courier> findByAverageRatingsGreaterThanEqual(Double minimumRating) {
        return courierJpaRepository.findByAverageRatingsGreaterThanEqual(minimumRating).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Courier> findByHireDateAfter(OffsetDateTime hireDate) {
        return courierJpaRepository.findByHireDateAfter(hireDate).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Courier> findByVehicleType(String vehicleType) {
        return courierJpaRepository.findByVehicleType(vehicleType).stream()
                .map(mapper::mapFromEntity)
                .toList();
    }
}
