package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.AddressEntity;

import java.util.List;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {

    List<AddressEntity> findByCity(String city);
    List<AddressEntity> findByCustomerId(Long customerId);
    List<AddressEntity> findByRestaurantId(Long restaurantId);
    boolean existsByCityAndStreet(String city, String street);
}