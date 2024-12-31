package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.yummy.infrastructure.database.entity.CustomerEntity;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
}
