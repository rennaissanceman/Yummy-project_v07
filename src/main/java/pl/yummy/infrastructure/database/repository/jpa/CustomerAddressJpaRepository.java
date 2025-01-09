package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.CustomerAddressEntity;

import java.util.Optional;

@Repository
public interface CustomerAddressJpaRepository extends JpaRepository<CustomerAddressEntity, Long> {

    // Niestandardowe metody
    Optional<CustomerAddressEntity> findByCustomerIdAndDefaultAddressTrue(Long customerId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM CustomerAddressEntity c WHERE c.customer.id = :customerId AND c.defaultAddress = TRUE")
    boolean existsDefaultByCustomerId(@Param("customerId") Long customerId);
}
