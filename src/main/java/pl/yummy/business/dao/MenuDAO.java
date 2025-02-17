package pl.yummy.business.dao;

import pl.yummy.domain.Customer;
import pl.yummy.domain.Menu;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface MenuDAO {

    // Find a menu by its unique name
    Optional<Menu> findByMenuName(String menuName);

    // Find all menus for a specific restaurant ID
    List<Menu> findByRestaurant_RestaurantId(Long restaurantId);

    // Find menus valid within a specific date range
    List<Menu> findByValidFromBeforeAndValidToAfter(OffsetDateTime startDate, OffsetDateTime endDate);

    // Find menus created after a specific date
    List<Menu> findByCreatedAtAfter(OffsetDateTime createdAt);

    // Find menus by description containing a keyword
    List<Menu> findByDescriptionContainingIgnoreCase(String keyword);

    Menu save(Menu menu);


/*
    List<Menu> findCurrentlyActiveMenus();
*/


}
