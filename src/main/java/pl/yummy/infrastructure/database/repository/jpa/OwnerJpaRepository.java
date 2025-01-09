package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.OwnerEntity;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerEntity, Long> {

    // Wszystkie operacje CRUD obsługiwane domyślnie
}
