package pl.yummy.business.dao;

import pl.yummy.domain.Address;
import pl.yummy.infrastructure.database.entity.AddressEntity;

import java.util.List;
import java.util.Optional;

public interface AddressDAO {

    List<Address> findByCountry(String country);
    List<Address> findByCity(String city);
    List<Address> findByStreet(String street);
    List<Address> findByPostalCode(String postalCode);
    List<Address> findByCustomerId(Long customerId);
    List<Address> findUnusedAddresses(); // Adresy nieprzypisane do żadnej innej encji
    boolean existsByCityAndStreet(String city, String street);


}
