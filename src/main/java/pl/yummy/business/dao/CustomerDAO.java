package pl.yummy.business.dao;

import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    // Niestandardowe metody oparte na konwencji nazw
    Optional<CustomerEntity> findByEmail(String email);
    Optional<CustomerEntity> findByPhone(String phone);
    List<CustomerEntity> findByCompanyName(String companyName);
    List<CustomerEntity> findBySurname(String surname);
    List<CustomerEntity> findByVatNumber(String vatNumber);
    List<CustomerEntity> findByCity(String city);
    List<CustomerEntity> findByStreet(String street);
}
