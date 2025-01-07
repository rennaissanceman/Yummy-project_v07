package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.AvailableDeliveryAreaDAO;
import pl.yummy.infrastructure.database.entity.AvailableDeliveryAreaEntity;

import java.util.List;

@Repository
@AllArgsConstructor
public class AvailableDeliveryAreaRepository implements AvailableDeliveryAreaDAO {
    @Override
    public List<AvailableDeliveryAreaEntity> findByRestaurantId(Long restaurantId) {
        return null;
    }
}
