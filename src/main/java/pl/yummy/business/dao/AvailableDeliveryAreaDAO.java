package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.AvailableDeliveryAreaEntity;

import java.util.List;
import java.util.Optional;

public interface AvailableDeliveryAreaDAO {


    /* CRUD */
    /* create */
    AvailableDeliveryAreaEntity createAvailableDeliveryArea(AvailableDeliveryAreaEntity availableDeliveryArea);

    /* read */
    Optional<AvailableDeliveryAreaEntity> findAvailableDeliveryAreaById(Long availableDeliveryAreaId);

    List<AvailableDeliveryAreaEntity> findAvailableDeliveryAreasByRestaurantId(Long restaurantId);

    List<AvailableDeliveryAreaEntity> findAllAvailableDeliveryAreas();


    /* update */

    AvailableDeliveryAreaEntity updateAvailableDeliveryArea(AvailableDeliveryAreaEntity availableDeliveryArea);

    /* delete */

    void deleteAvailableDeliveryArea(Long availableDeliveryAreaId);




}
