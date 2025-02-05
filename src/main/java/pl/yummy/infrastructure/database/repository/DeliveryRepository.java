package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.DeliveryDAO;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;
import pl.yummy.infrastructure.database.repository.jpa.DeliveryJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.DeliveryEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class DeliveryRepository implements DeliveryDAO {

    private final DeliveryJpaRepository deliveryJpaRepository;
    private final DeliveryEntityMapper deliveryEntityMapper;

    @Override
    public List<Delivery> findByCourier_CourierId(Long courierId) {
        return deliveryJpaRepository.findByCourier_CourierId(courierId)
                .stream()
                .map(deliveryEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Delivery> findByDeliveryStatus(DeliveryStatusEnumDomain deliveryStatus) {
        return deliveryJpaRepository.findByDeliveryStatus(
                        // zakładamy zgodność nazw enumów
                        DeliveryStatusEnumDomain.valueOf(deliveryStatus.name())
                )
                .stream()
                .map(deliveryEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Delivery> findByStarTimeAfter(OffsetDateTime startTime) {
        return deliveryJpaRepository.findByStarTimeAfter(startTime)
                .stream()
                .map(deliveryEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Delivery> findByActualDeliveryDateTimeAfterAndEstimatedDeliveryTimeIsNotNull(OffsetDateTime actualDeliveryDateTime) {
        return deliveryJpaRepository.findByActualDeliveryDateTimeAfterAndEstimatedDeliveryTimeIsNotNull(actualDeliveryDateTime)
                .stream()
                .map(deliveryEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Delivery> findByAvailableDeliveryArea_AvailableDeliveryAreaId(Long deliveryAreaId) {
        return deliveryJpaRepository.findByAvailableDeliveryArea_AvailableDeliveryAreaId(deliveryAreaId)
                .stream()
                .map(deliveryEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

}
