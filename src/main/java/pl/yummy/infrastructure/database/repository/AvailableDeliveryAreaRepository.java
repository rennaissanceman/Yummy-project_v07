package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.AvailableDeliveryAreaDAO;
import pl.yummy.domain.AvailableDeliveryArea;
import pl.yummy.domain.Restaurant;

import java.util.List;

@Repository
@AllArgsConstructor
public class AvailableDeliveryAreaRepository implements AvailableDeliveryAreaDAO {

    @Override
    public List<AvailableDeliveryArea> findByRestaurantId(Long restaurantId) {
        return null;
    }

    @Override
    public List<AvailableDeliveryArea> findByRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public List<AvailableDeliveryArea> findByAddressId(Integer addressId) {
        return null;
    }

    @Override
    public List<AvailableDeliveryArea> findByActiveOrders() {
        return null;
    }
}
