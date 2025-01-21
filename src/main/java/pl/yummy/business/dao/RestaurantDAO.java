package pl.yummy.business.dao;

import pl.yummy.domain.Restaurant;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {

    Optional<Restaurant> findByRestaurantName(String name);
    List<Restaurant> findByOwnerId(Integer ownerId);
    List<Restaurant> findByCuisineType(CuisineTypeEnumDomain cuisineType);

}
