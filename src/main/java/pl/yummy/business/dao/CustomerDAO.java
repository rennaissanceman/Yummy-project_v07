package pl.yummy.business.dao;

import pl.yummy.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    // Find a customer by their unique customer number
    Optional<Customer> findByCustomerNumber(String customerNumber);

    // Find all customers with a specific company name
    List<Customer> findByCompanyName(String companyName);

    // Find all customers with a specific email
    Optional<Customer> findByUserAuth_Email(String email);

    // Find customers who are companies
    List<Customer> findByIsCompanyTrue();

    // Find customers by their last name
    List<Customer> findByCustomerSurname(String surname);

    Customer saveCustomer(Customer customer);

    List<Customer> findAll();
}
