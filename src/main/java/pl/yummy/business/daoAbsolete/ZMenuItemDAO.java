package pl.yummy.business.daoAbsolete;

import pl.yummy.infrastructure.database.entity.MenuItemEntity;
import pl.yummy.infrastructure.database.entity.enums.DietType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ZMenuItemDAO {

    /* CRUD */
    /* create */
    MenuItemEntity createMenuItem(MenuItemEntity menuItem);

    /* read */
    Optional<MenuItemEntity> findMenuItemById(Long menuItemId);
    List<MenuItemEntity> findMenuItemsByAvailability(Boolean isAvailable);
    List<MenuItemEntity> findMenuItemsByDietType(DietType dietType);
    List<MenuItemEntity> findMenuItemsByMenuId(Long menuId);
    List<MenuItemEntity> findMenuItemsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<MenuItemEntity> findAllMenuItems();

    /* update */
    MenuItemEntity updateMenuItem(MenuItemEntity menuItem);

    /* delete */
    void deleteMenuItem(Long menuItemId);


    boolean existsMenuItemByName(String itemName);
    long countMenuItemsByMenuId(Long menuId);










}
