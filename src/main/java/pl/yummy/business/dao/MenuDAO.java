package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.MenuEntity;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.List;
import java.util.Optional;

public interface MenuDAO {

    /* CRUD */
    /* create */
    MenuEntity createMenu(MenuEntity menu);

    /* read */
    Optional<MenuEntity> findMenuById(Long menuId);
    List<MenuEntity> findMenusByRestaurantId(Long restaurantId);

    List<MenuEntity> findAllMenus();

    /* update */
    void updateMenu(MenuEntity menu);

    /* delete */
    void deleteMenu(Long menuId);

}

