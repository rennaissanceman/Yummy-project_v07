package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.CourierEntity;

import java.util.List;

@Repository
public interface CourierJpaRepository extends JpaRepository<CourierEntity, Long> {

    // Niestandardowe metody
    @Query("SELECT c FROM CourierEntity c WHERE c.available = true")
    List<CourierEntity> findAvailableCouriers();

    @Query("SELECT c FROM CourierEntity c WHERE c.available = true AND c.restaurant.id = :restaurantId")
    List<CourierEntity> findAvailableCouriersByRestaurant(@Param("restaurantId") Long restaurantId);
}
