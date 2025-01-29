package pl.yummy.business.dao;

import pl.yummy.domain.Restaurant;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;

import java.util.List;
import java.util.Optional;

public interface RestaurantDAO {


    // Find a restaurant by its name
    Optional<Restaurant> findByRestaurantName(String restaurantName);

    // Find all restaurants by a specific cuisine type
    List<Restaurant> findByCuisineType(CuisineTypeEnumDomain cuisineType);

    // Find restaurants with a minimum average rating
    List<Restaurant> findByAverageRatingGreaterThanEqual(Double minimumRating);

    // Find restaurants owned by a specific owner ID
    List<Restaurant> findByOwner_OwnerId(Long ownerId);

}
