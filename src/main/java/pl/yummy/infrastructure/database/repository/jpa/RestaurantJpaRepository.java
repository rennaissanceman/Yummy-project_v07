package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long> {

    @Query("SELECT r FROM RestaurantEntity r WHERE r.cuisineType IN :cuisineTypes")
    List<RestaurantEntity> findByCuisineTypeIn(@Param("cuisineTypes") List<String> cuisineTypes);

    List<RestaurantEntity> findByCity(String city);
    List<RestaurantEntity> findByStreet(String street);
}
