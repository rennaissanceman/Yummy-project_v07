package pl.yummy.business.dao;

import pl.yummy.domain.AvailableDeliveryArea;
import pl.yummy.domain.Restaurant;
import pl.yummy.infrastructure.database.entity.AvailableDeliveryAreaEntity;

import java.util.List;

public interface AvailableDeliveryAreaDAO {

    List<AvailableDeliveryArea> findByRestaurantId(Long restaurantId);
    List<AvailableDeliveryArea> findByRestaurant(Restaurant restaurant);
    List<AvailableDeliveryArea> findByAddressId(Integer addressId);
    List<AvailableDeliveryArea> findByActiveOrders(); // Obszary z aktywnymi zamówieniami

}
