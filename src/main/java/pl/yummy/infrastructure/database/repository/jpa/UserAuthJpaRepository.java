package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.UserAuthEntity;

@Repository
public interface UserAuthJpaRepository extends JpaRepository<UserAuthEntity, Long> {
}
