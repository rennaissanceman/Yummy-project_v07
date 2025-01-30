package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long> {

    // Find a restaurant by its name
    Optional<RestaurantEntity> findByRestaurantName(String restaurantName);

    // Find all restaurants by a specific cuisine type
    List<RestaurantEntity> findByCuisineType(CuisineTypeEnumDomain cuisineType);

    // Find restaurants with a minimum average rating
    List<RestaurantEntity> findByAverageRatingGreaterThanEqual(Double minimumRating);

    // Find restaurants owned by a specific owner ID
    List<RestaurantEntity> findByOwner_OwnerId(Long ownerId);

/*
    @Query("SELECT r FROM RestaurantEntity r WHERE r.cuisineType IN :cuisineTypes")
    List<RestaurantEntity> findByCuisineTypeIn(@Param("cuisineTypes") List<String> cuisineTypes);

    List<RestaurantEntity> findByCity(String city);
    List<RestaurantEntity> findByStreet(String street);
    */
}
