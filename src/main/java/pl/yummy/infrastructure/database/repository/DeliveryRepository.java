package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.DeliveryDAO;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DeliveryRepository implements DeliveryDAO {
    @Override
    public Optional<DeliveryEntity> findByOrderId(Long orderId) {
        return Optional.empty();
    }

    @Override
    public Optional<DeliveryEntity> findByCourierId(Long courierId) {
        return Optional.empty();
    }

    @Override
    public List<DeliveryEntity> findByStatusIn(List<String> statuses) {
        return null;
    }
}
