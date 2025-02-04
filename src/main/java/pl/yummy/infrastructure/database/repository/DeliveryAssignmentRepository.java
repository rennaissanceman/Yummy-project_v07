package pl.yummy.infrastructure.database.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.DeliveryAssignmentDAO;
import pl.yummy.infrastructure.database.entity.CourierEntity;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;
import pl.yummy.infrastructure.database.entity.enums.CourierStatusEnumEntity;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatusEnumEntity;
import pl.yummy.infrastructure.database.repository.jpa.CourierJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.DeliveryJpaRepository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class DeliveryAssignmentRepository implements DeliveryAssignmentDAO {

    private final DeliveryJpaRepository deliveryJpaRepository;
    private final CourierJpaRepository courierJpaRepository;

    @Override
    @Transactional
    public void assignCourierToDelivery(Long deliveryId) {
        DeliveryEntity deliveryEntity = deliveryJpaRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));

        Optional<CourierEntity> availableCourier = courierJpaRepository
                .findFirstByCourierStatus(CourierStatusEnumEntity.AVAILABLE);


        availableCourier.ifPresentOrElse(courier -> {
            deliveryEntity.setCourier(courier);
            deliveryEntity.setDeliveryStatus(DeliveryStatusEnumEntity.ASSIGNED);
            courier.setCourierStatus(CourierStatusEnumEntity.BUSY);

            courierJpaRepository.save(courier);
            deliveryJpaRepository.save(deliveryEntity);
        }, () -> {
            throw new IllegalStateException("No available couriers");
        });
    }

    /*
    - Dedykowana logika przypisywania dostawców do dostawy.
    - Unikanie zacięć poprzez zapewnienie, że tylko dostępni kurierzy są przypisywani.

    Zarządzanie dostawcami --> Przypisywanie dostępnych kurierów do zamówień
    */
}
