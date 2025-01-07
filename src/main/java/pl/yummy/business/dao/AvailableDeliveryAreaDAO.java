package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.AvailableDeliveryAreaEntity;

import java.util.List;

public interface AvailableDeliveryAreaDAO {

    List<AvailableDeliveryAreaEntity> findByRestaurantId(Long restaurantId);
}
