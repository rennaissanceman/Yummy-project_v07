package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.domain.enums.DietTypeEnumDomain;
import pl.yummy.infrastructure.database.entity.MenuItemEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Long> {

    // Find a menu item by its unique name
    Optional<MenuItemEntity> findByItemName(String itemName);

    // Find all menu items by a specific menu ID
    List<MenuItemEntity> findByMenu_MenuId(Long menuId);

    // Find all menu items by diet type
    List<MenuItemEntity> findByDietType(DietTypeEnumDomain dietType);

    // Find menu items that are currently available
    List<MenuItemEntity> findByIsAvailableTrue();

    // Find menu items containing a keyword in the description
    List<MenuItemEntity> findByDescriptionContainingIgnoreCase(String keyword);

  /*
    List<MenuItemEntity> findByAvailability(Boolean isAvailable);
    List<MenuItemEntity> findByDietType(DietType dietType);
    List<MenuItemEntity> findByMenuId(Long menuId);

    @Query("SELECT m FROM MenuItemEntity m WHERE m.price BETWEEN :minPrice AND :maxPrice")
    List<MenuItemEntity> findByPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    boolean existsByName(String itemName);
    long countByMenuId(Long menuId);
    */
}
