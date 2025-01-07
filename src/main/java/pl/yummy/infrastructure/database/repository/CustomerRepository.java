package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.CustomerDAO;
import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepository implements CustomerDAO {
    @Override
    public Optional<CustomerEntity> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerEntity> findByPhone(String phone) {
        return Optional.empty();
    }

    @Override
    public List<CustomerEntity> findByCompanyName(String companyName) {
        return null;
    }

    @Override
    public List<CustomerEntity> findBySurname(String surname) {
        return null;
    }

    @Override
    public List<CustomerEntity> findByVatNumber(String vatNumber) {
        return null;
    }

    @Override
    public List<CustomerEntity> findByCity(String city) {
        return null;
    }

    @Override
    public List<CustomerEntity> findByStreet(String street) {
        return null;
    }
}
