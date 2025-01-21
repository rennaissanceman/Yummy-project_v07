package pl.yummy.business.dao;

import pl.yummy.domain.Menu;
import pl.yummy.domain.MenuItem;
import pl.yummy.domain.enums.DietTypeEnumDomain;

import java.math.BigDecimal;
import java.util.List;

public interface MenuItemDAO {

    List<MenuItem> findByMenuId(Integer menuId);
    List<MenuItem> findByDietType(DietTypeEnumDomain dietType);
    List<MenuItem> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<MenuItem> findByDietType(String dietType);

    List<MenuItem> findAvailableItems();

    List<MenuItem> findByPriceBetween(Double minPrice, Double maxPrice);


    List<MenuItem> findByMenu(Menu menu);
    List<MenuItem> findByIsAvailable(Boolean isAvailable);



    List<MenuItem> findByAvailability(Boolean isAvailable);

    List<MenuItem> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);



}
