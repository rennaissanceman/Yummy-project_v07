package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.MenuEntity;

import java.util.List;

public interface MenuDAO {

    // Niestandardowa metoda
    List<MenuEntity> findByRestaurantId(Long restaurantId);
}
