package pl.yummy.business.daoAbsolete;

import pl.yummy.infrastructure.database.entity.AddressEntity;

import java.util.List;
import java.util.Optional;

public interface ZAddressDAO {

    /* CRUD */
    /* create */
    AddressEntity createAddress(AddressEntity address);

    /* read */
    Optional<AddressEntity> findAddressById(Long addressId);
    List<AddressEntity> findAddressByCity(String city);
    List<AddressEntity> findAddressesByCustomerId(Long customerId);
    List<AddressEntity> findAddressesByRestaurantId(Long restaurantId);
    List<AddressEntity> findAllAddresses();


    /* update */
    AddressEntity updateAddress(AddressEntity address);

    /* delete */
    void deleteAddress (Long addressId);



    boolean existsAddressByCityAndStreet(String city, String street);
}
