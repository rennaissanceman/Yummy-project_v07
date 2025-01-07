package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.CourierDAO;
import pl.yummy.infrastructure.database.entity.CourierEntity;

import java.util.List;

@Repository
@AllArgsConstructor
public class CourierRepository implements CourierDAO {
    @Override
    public List<CourierEntity> findAvailableCouriers() {
        return null;
    }

    @Override
    public List<CourierEntity> findAvailableCouriersByRestaurant(Long restaurantId) {
        return null;
    }
}
