package pl.yummy.business.dao;

import pl.yummy.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    Optional<Customer> findByCustomerNumber(String customerNumber);
    List<Customer> findByIsCompanyTrue();
    List<Customer> findByCustomerNameAndCustomerSurname(String name, String surname);

    List<Customer> findByCompanyName(String companyName);

    List<Customer> findCustomersWithOrders();

    List<Customer> findByOrderCountGreaterThan(Integer minimumOrders);


    List<Customer> findByIsCompany(Boolean isCompany);
    List<Customer> findByCustomerNameContainingIgnoreCase(String name);
    List<Customer> findByOrdersNotEmpty();




    List<Customer> findByCompanyStatus(Boolean isCompany);

    List<Customer> findByName(String name);

    List<Customer> findWithOrders();
}
