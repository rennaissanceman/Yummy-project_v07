package pl.yummy.business.dao;

import pl.yummy.domain.Menu;
import pl.yummy.domain.Restaurant;

import java.time.OffsetDateTime;
import java.util.List;

public interface MenuDAO {

    List<Menu> findByRestaurantId(Integer restaurantId);
    List<Menu> findByValidFromBeforeAndValidToAfter(OffsetDateTime startDate, OffsetDateTime endDate);

    List<Menu> findCurrentlyActiveMenus();
    Menu findByName(String name);


    List<Menu> findByRestaurant(Restaurant restaurant);
    List<Menu> findByMenuNameContainingIgnoreCase(String menuName);




    List<Menu> findValidMenus();




}
