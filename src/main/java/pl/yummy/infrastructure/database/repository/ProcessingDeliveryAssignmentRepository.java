package pl.yummy.infrastructure.database.repository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.yummy.business.dao.ProcessingDeliveryAssignmentDAO;
import pl.yummy.domain.Delivery;
import pl.yummy.domain.RequestDeliveryAssignment;
import pl.yummy.infrastructure.database.entity.CourierEntity;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;
import pl.yummy.infrastructure.database.entity.enums.CourierStatusEnumEntity;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatusEnumEntity;
import pl.yummy.infrastructure.database.repository.jpa.CourierJpaRepository;
import pl.yummy.infrastructure.database.repository.jpa.DeliveryJpaRepository;
import pl.yummy.infrastructure.database.repository.mapper.DeliveryEntityMapper;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProcessingDeliveryAssignmentRepository implements ProcessingDeliveryAssignmentDAO {

    /*
    Repozytorium do przypisywania kuriera do zamówienia. Przyjmuje DeliveryAssignmentRequest i mapuje je na nowy
    obiekt domenowy Delivery (lub modyfikuje istniejący stan dostawy).
    – Odpowiada za przypisanie kuriera do zamówienia. Przyjmuje obiekt DeliveryAssignmentRequest,
    mapuje dane do encji powiązanej z dostawą (DeliveryEntity) oraz aktualizuje stan zamówienia,
    aby odzwierciedlić przypisanie kuriera.
    */

    private final DeliveryJpaRepository deliveryJpaRepository;
    private final DeliveryEntityMapper deliveryEntityMapper;


    private final CourierJpaRepository courierJpaRepository;


    @Override
    @Transactional
    public void assignDelivery(RequestDeliveryAssignment request) {
        // Mapujemy dane z requestu na domenowy obiekt Delivery.
        // (Przykładowo przyjmujemy, że numer zamówienia jest używany jako deliveryNumber.)
        Delivery delivery = Delivery.builder()
                .deliveryNumber(request.getOrderNumber())
                // Zakładamy, że dalsze dane (np. przypisanie kuriera) będą ustawione przez logikę aplikacyjną
                .starTime(request.getAssignmentDateTime())
                .deliveryNotes(request.getComment())
                .build();
        var deliveryEntity = deliveryEntityMapper.mapToEntity(delivery);
        deliveryJpaRepository.saveAndFlush(deliveryEntity);
    }


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
