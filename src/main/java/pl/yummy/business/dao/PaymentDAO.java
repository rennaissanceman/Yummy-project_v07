package pl.yummy.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.yummy.infrastructure.database.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {

    Optional<PaymentEntity> findByOrderId(Long orderId);

    @Query("SELECT p FROM PaymentEntity p WHERE p.status IN :statuses")
    List<PaymentEntity> findByStatusIn(@Param("statuses") List<String> statuses);
}
