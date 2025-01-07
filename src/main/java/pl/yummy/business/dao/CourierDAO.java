package pl.yummy.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.yummy.infrastructure.database.entity.CourierEntity;

import java.util.List;

public interface CourierDAO {

    // Niestandardowe metody
    @Query("SELECT c FROM CourierEntity c WHERE c.available = true")
    List<CourierEntity> findAvailableCouriers();

    @Query("SELECT c FROM CourierEntity c WHERE c.available = true AND c.restaurant.id = :restaurantId")
    List<CourierEntity> findAvailableCouriersByRestaurant(@Param("restaurantId") Long restaurantId);
}
