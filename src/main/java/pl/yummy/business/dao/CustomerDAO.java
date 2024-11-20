package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerDAO {

    Optional<CustomerEntity> findCustomerByUserEmail(String email);


}
