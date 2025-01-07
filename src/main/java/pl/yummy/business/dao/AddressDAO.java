package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.AddressEntity;

import java.util.List;

public interface AddressDAO {

    List<AddressEntity> findByCity(String city);
    List<AddressEntity> findByCustomerId(Long customerId);
    List<AddressEntity> findByRestaurantId(Long restaurantId);
    boolean existsByCityAndStreet(String city, String street);
}
