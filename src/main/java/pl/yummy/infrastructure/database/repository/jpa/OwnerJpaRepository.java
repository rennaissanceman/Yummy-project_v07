package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.OwnerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerJpaRepository extends JpaRepository<OwnerEntity, Long> {

    // Find an owner by their unique owner number
    Optional<OwnerEntity> findByOwnerNumber(String ownerNumber);

    // Find an owner by their name
    Optional<OwnerEntity> findByOwnerName(String ownerName);


    @Query("SELECT o FROM OwnerEntity o WHERE size(o.restaurants) >= :minCount")
    List<OwnerEntity> findOwnersWithAtLeastRestaurants(@Param("minCount") Long minCount);
}
