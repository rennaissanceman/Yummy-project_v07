package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.OrdersEntity;

import java.util.List;

@Repository
public interface OrdersJpaRepository extends JpaRepository<OrdersEntity, Long> {

    // Niestandardowa metoda
    List<OrdersEntity> findByCustomerId(Long customerId);
}
