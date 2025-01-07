package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.AddressDAO;
import pl.yummy.infrastructure.database.entity.AddressEntity;

import java.util.List;

@Repository
@AllArgsConstructor
public class AddressRepository implements AddressDAO {
    @Override
    public List<AddressEntity> findByCity(String city) {
        return null;
    }

    @Override
    public List<AddressEntity> findByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<AddressEntity> findByRestaurantId(Long restaurantId) {
        return null;
    }

    @Override
    public boolean existsByCityAndStreet(String city, String street) {
        return false;
    }
}
