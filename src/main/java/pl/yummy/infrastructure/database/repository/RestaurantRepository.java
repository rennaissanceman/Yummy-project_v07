package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.RestaurantDAO;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantRepository implements RestaurantDAO {
    @Override
    public List<RestaurantEntity> findByCuisineTypeIn(List<String> cuisineTypes) {
        return null;
    }

    @Override
    public List<RestaurantEntity> findByCity(String city) {
        return null;
    }

    @Override
    public List<RestaurantEntity> findByStreet(String street) {
        return null;
    }
}
