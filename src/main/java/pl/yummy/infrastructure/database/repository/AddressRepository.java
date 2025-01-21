package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.AddressDAO;
import pl.yummy.domain.Address;

import java.util.List;

@Repository
@AllArgsConstructor
public class AddressRepository implements AddressDAO {

    @Override
    public List<Address> findByCountry(String country) {
        return null;
    }

    @Override
    public List<Address> findByCity(String city) {
        return null;
    }

    @Override
    public List<Address> findByStreet(String street) {
        return null;
    }

    @Override
    public List<Address> findByPostalCode(String postalCode) {
        return null;
    }

    @Override
    public List<Address> findByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<Address> findUnusedAddresses() {
        return null;
    }

    @Override
    public boolean existsByCityAndStreet(String city, String street) {
        return false;
    }
}
