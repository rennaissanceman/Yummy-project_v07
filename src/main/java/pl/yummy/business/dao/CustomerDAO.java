package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
        Optional<CustomerEntity> findById(Long customerId);
        Optional<CustomerEntity> findByCustomerNumber(String customerNumber);
        List<CustomerEntity> findAll();
        CustomerEntity save(CustomerEntity customer);
        void deleteById(Long customerId);
        boolean existsById(Long customerId);
}
