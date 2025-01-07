package pl.yummy.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.yummy.infrastructure.database.entity.MenuItemEntity;
import pl.yummy.infrastructure.database.entity.enums.DietType;

import java.math.BigDecimal;
import java.util.List;

public interface MenuItemDAO {

    List<MenuItemEntity> findByAvailability(Boolean isAvailable);
    List<MenuItemEntity> findByDietType(DietType dietType);
    List<MenuItemEntity> findByMenuId(Long menuId);

    @Query("SELECT m FROM MenuItemEntity m WHERE m.price BETWEEN :minPrice AND :maxPrice")
    List<MenuItemEntity> findByPriceBetween(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    boolean existsByName(String itemName);
    long countByMenuId(Long menuId);
}
