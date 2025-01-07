package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.MenuItemDAO;
import pl.yummy.infrastructure.database.entity.MenuItemEntity;
import pl.yummy.infrastructure.database.entity.enums.DietType;

import java.math.BigDecimal;
import java.util.List;

@Repository
@AllArgsConstructor
public class MenuItemRepository implements MenuItemDAO {
    @Override
    public List<MenuItemEntity> findByAvailability(Boolean isAvailable) {
        return null;
    }

    @Override
    public List<MenuItemEntity> findByDietType(DietType dietType) {
        return null;
    }

    @Override
    public List<MenuItemEntity> findByMenuId(Long menuId) {
        return null;
    }

    @Override
    public List<MenuItemEntity> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return null;
    }

    @Override
    public boolean existsByName(String itemName) {
        return false;
    }

    @Override
    public long countByMenuId(Long menuId) {
        return 0;
    }
}
