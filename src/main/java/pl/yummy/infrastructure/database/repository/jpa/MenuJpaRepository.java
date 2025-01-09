package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.MenuEntity;

import java.util.List;

@Repository
public interface MenuJpaRepository extends JpaRepository<MenuEntity, Long> {

    // Niestandardowa metoda
    List<MenuEntity> findByRestaurantId(Long restaurantId);
}
