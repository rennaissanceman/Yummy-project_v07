package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    /* CRUD */
    /* create */

    CustomerEntity createCustomer(CustomerEntity customer);

    /* read */

    Optional<CustomerEntity> findCustomerById(Long customerId);
    Optional<CustomerEntity> findCustomerByEmail(String email);
    Optional<CustomerEntity> findCustomerByPhone(String phone);
    List<CustomerEntity> findCustomerByCompanyName(String companyName);
    List<CustomerEntity> findCustomerBySurname(String customerSurname);
    List<CustomerEntity> findCustomerByVatNumber(String vatNumber);
    List<CustomerEntity> findCustomerByCity(String city);
    List<CustomerEntity> findCustomerByStreet(String street);

    List<CustomerEntity> findAllCustomers();

    /* update */
    void updateCustomer(CustomerEntity customer);

    /* delete */
    void deleteCustomer(Long customerId);



}
