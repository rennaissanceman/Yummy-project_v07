package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {

    /* CRUD */
    /* create */
    RestaurantEntity createRestaurant(RestaurantEntity restaurant);

    /* read */
    Optional<RestaurantEntity> findRestaurantById(Long id);
    List<RestaurantEntity> findRestaurantsByCuisineType(String cuisineType);
    List<RestaurantEntity> findRestaurantsByCity(String city);
    List<RestaurantEntity> findRestaurantsByStreet(String street);

    List<RestaurantEntity> getAllRestaurants();

    /* update */
    void updateRestaurant(RestaurantEntity restaurant);

    /* delete */
    void deleteRestaurant(Long restaurantId);

}
