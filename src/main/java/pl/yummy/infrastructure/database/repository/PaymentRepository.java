package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.PaymentDAO;
import pl.yummy.infrastructure.database.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PaymentRepository implements PaymentDAO {
    @Override
    public Optional<PaymentEntity> findByOrderId(Long orderId) {
        return Optional.empty();
    }

    @Override
    public List<PaymentEntity> findByStatusIn(List<String> statuses) {
        return null;
    }
}
