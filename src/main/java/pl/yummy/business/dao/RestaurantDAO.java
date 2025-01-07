package pl.yummy.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

public interface RestaurantDAO {

    @Query("SELECT r FROM RestaurantEntity r WHERE r.cuisineType IN :cuisineTypes")
    List<RestaurantEntity> findByCuisineTypeIn(@Param("cuisineTypes") List<String> cuisineTypes);

    List<RestaurantEntity> findByCity(String city);
    List<RestaurantEntity> findByStreet(String street);
}
