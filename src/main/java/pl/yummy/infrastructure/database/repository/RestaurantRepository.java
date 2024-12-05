package pl.yummy.infrastructure.database.repository;

import pl.yummy.business.dao.RestaurantDAO;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.List;
import java.util.Optional;

public class RestaurantRepository implements RestaurantDAO {
    @Override
    public RestaurantEntity createRestaurant(RestaurantEntity restaurant) {
        return null;
    }

    @Override
    public Optional<RestaurantEntity> findRestaurantById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<RestaurantEntity> findRestaurantsByCuisineType(String cuisineType) {
        return null;
    }

    @Override
    public List<RestaurantEntity> findRestaurantsByCity(String city) {
        return null;
    }

    @Override
    public List<RestaurantEntity> findRestaurantsByStreet(String street) {
        return null;
    }

    @Override
    public List<RestaurantEntity> getAllRestaurants() {
        return null;
    }

    @Override
    public void updateRestaurant(RestaurantEntity restaurant) {

    }

    @Override
    public void deleteRestaurant(Long restaurantId) {

    }
}
