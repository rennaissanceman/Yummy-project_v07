package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryJpaRepository extends JpaRepository<DeliveryEntity, Long> {

    // Niestandardowe metody
    Optional<DeliveryEntity> findByOrderId(Long orderId);
    Optional<DeliveryEntity> findByCourierId(Long courierId);

    @Query("SELECT d FROM DeliveryEntity d WHERE d.status IN :statuses")
    List<DeliveryEntity> findByStatusIn(@Param("statuses") List<String> statuses);
}
