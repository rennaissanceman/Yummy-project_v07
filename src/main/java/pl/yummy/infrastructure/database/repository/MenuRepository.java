package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.MenuDAO;
import pl.yummy.infrastructure.database.entity.MenuEntity;

import java.util.List;

@Repository
@AllArgsConstructor
public class MenuRepository implements MenuDAO {
    @Override
    public List<MenuEntity> findByRestaurantId(Long restaurantId) {
        return null;
    }
}
