package pl.yummy.business.daoAbsolete;

import pl.yummy.infrastructure.database.entity.CustomerAddressEntity;

import java.util.List;
import java.util.Optional;

public interface ZCustomerAddressDAO {

    /* CRUD */
    /* create */
    CustomerAddressEntity createCustomerAddress(CustomerAddressEntity customerAddress);

    /* read */
    Optional<CustomerAddressEntity> findCustomerAddressById(Long customerAddressId);
    List<CustomerAddressEntity> findAllCustomerAddresses();
    List<CustomerAddressEntity> findCustomerAddressesByCustomerId(Long customerId);
    Optional<CustomerAddressEntity> findDefaultCustomerAddressByCustomerId(Long customerId);

    /* update */
    CustomerAddressEntity updateCustomerAddress(CustomerAddressEntity customerAddress);

    /* delete */
    void deleteCustomerAddress(Long customerAddressId);
    void deleteCustomerAddressesByCustomerId(Long customerId);


    boolean existsDefaultCustomerAddressByCustomerId(Long customerId);
    long countCustomerAddressesByCustomerId(Long customerId);

}
