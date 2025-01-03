package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.OrdersEntity;

@Repository
public interface OrdersJpaRepository extends JpaRepository<OrdersEntity, Long> {
}
