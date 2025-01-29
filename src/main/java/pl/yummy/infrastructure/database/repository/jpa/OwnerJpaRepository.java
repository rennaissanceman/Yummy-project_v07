package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.domain.Owner;
import pl.yummy.infrastructure.database.entity.OwnerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerEntity, Long> {

    // Find an owner by their unique owner number
    Optional<OwnerEntity> findByOwnerNumber(String ownerNumber);

    // Find an owner by their name
    Optional<OwnerEntity> findByOwnerName(String ownerName);

    // Find all owners who have more than a specified number of restaurants
    List<OwnerEntity> findByRestaurants_SizeGreaterThanEqual(Long minimumRestaurants);
}
