package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.MenuEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuJpaRepository extends JpaRepository<MenuEntity, Long> {

    // Find a menu by its unique name
    Optional<MenuEntity> findByMenuName(String menuName);

    // Find all menus for a specific restaurant ID
    List<MenuEntity> findByRestaurant_RestaurantId(Long restaurantId);

    // Find menus valid within a specific date range
    List<MenuEntity> findByValidFromBeforeAndValidToAfter(OffsetDateTime startDate, OffsetDateTime endDate);

    // Find menus created after a specific date
    List<MenuEntity> findByCreatedAtAfter(OffsetDateTime createdAt);

    // Find menus by description containing a keyword
    List<MenuEntity> findByDescriptionContainingIgnoreCase(String keyword);

/*
    // Niestandardowa metoda
    List<MenuEntity> findByRestaurantId(Long restaurantId);
    */
}
