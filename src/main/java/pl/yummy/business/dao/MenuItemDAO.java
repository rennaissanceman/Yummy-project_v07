package pl.yummy.business.dao;

import pl.yummy.domain.Menu;
import pl.yummy.domain.MenuItem;
import pl.yummy.domain.enums.DietTypeEnumDomain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MenuItemDAO {


    // Find a menu item by its unique name
    Optional<MenuItem> findByItemName(String itemName);

    // Find all menu items by a specific menu ID
    List<MenuItem> findByMenu_MenuId(Long menuId);

    // Find all menu items by diet type
    List<MenuItem> findByDietType(DietTypeEnumDomain dietType);

    // Find menu items that are currently available
    List<MenuItem> findByIsAvailableTrue();

    // Find menu items containing a keyword in the description
    List<MenuItem> findByDescriptionContainingIgnoreCase(String keyword);





    List<MenuItem> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<MenuItem> findByIsAvailable(Boolean isAvailable);





}
