package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.CourierEntity;

import java.util.List;
import java.util.Optional;

public interface CourierDAO {

    /* CRUD */
    /* create */
    CourierEntity createCourier(CourierEntity courier);

    /* read */
    Optional<CourierEntity> findCourierById(Long courierId);
    List<CourierEntity> findAvailableCouriers();
    List<CourierEntity> findAvailableCouriersByRestaurant(Long restaurantId);
    List<CourierEntity> findAllCouriers();

    /* update */
    CourierEntity updateCourier(CourierEntity courier);

    /* delete */
    void deleteCourier(Long courierId);

}

