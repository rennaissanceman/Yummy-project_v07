package pl.yummy.business.daoAbsolete;

import pl.yummy.infrastructure.database.entity.MenuEntity;

import java.util.List;
import java.util.Optional;

public interface ZMenuDAO {

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

