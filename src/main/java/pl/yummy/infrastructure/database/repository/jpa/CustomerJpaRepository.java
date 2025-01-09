package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    // Niestandardowe metody oparte na konwencji nazw
    Optional<CustomerEntity> findByEmail(String email);
    Optional<CustomerEntity> findByPhone(String phone);
    List<CustomerEntity> findByCompanyName(String companyName);
    List<CustomerEntity> findBySurname(String surname);
    List<CustomerEntity> findByVatNumber(String vatNumber);
    List<CustomerEntity> findByCity(String city);
    List<CustomerEntity> findByStreet(String street);
}
