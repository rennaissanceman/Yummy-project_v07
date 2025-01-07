package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.CustomerAddressDAO;
import pl.yummy.infrastructure.database.entity.CustomerAddressEntity;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerAddressRepository implements CustomerAddressDAO {
    @Override
    public Optional<CustomerAddressEntity> findByCustomerIdAndDefaultAddressTrue(Long customerId) {
        return Optional.empty();
    }

    @Override
    public boolean existsDefaultByCustomerId(Long customerId) {
        return false;
    }
}
