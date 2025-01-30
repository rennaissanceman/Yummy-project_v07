package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {


    Optional<CustomerEntity> findByCustomerNumber(String customerNumber);
    List<CustomerEntity> findByCompanyName(String companyName);
    Optional<CustomerEntity> findByUserAuth_Email(String email);
    List<CustomerEntity> findByIsCompanyTrue();
    List<CustomerEntity> findByCustomerSurname(String surname);

    boolean existBy
}
