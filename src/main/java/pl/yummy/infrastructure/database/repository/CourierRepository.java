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
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CourierRepository implements CourierDAO {

    private final CourierJpaRepository courierJpaRepository;
    private final CourierEntityMapper courierEntityMapper;

    @Override
    public Optional<Courier> findByCourierNumber(String courierNumber) {
        return courierJpaRepository.findByCourierNumber(courierNumber)
                .map(courierEntityMapper::mapFromEntity);
    }

    @Override
    public List<Courier> findByCourierStatus(CourierStatusEnumDomain courierStatus) {
        // Przekształcamy status domenowy na status encji – zakładamy, że nazwy enumów są zgodne.
        return courierJpaRepository.findByCourierStatus(
                        Enum.valueOf(
                                courierEntityMapper.getCourierStatusEntityClass(),
                                courierStatus.name()
                        )
                )
                .stream()
                .map(courierEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Courier> findByAverageRatingsGreaterThanEqual(Double minimumRating) {
        return courierJpaRepository.findByAverageRatingsGreaterThanEqual(minimumRating)
                .stream()
                .map(courierEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Courier> findByHireDateAfter(OffsetDateTime hireDate) {
        return courierJpaRepository.findByHireDateAfter(hireDate)
                .stream()
                .map(courierEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Courier> findByVehicleType(String vehicleType) {
        return courierJpaRepository.findByVehicleType(vehicleType)
                .stream()
                .map(courierEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
