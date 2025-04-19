package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.CustomerAddressEntity;

import java.util.Optional;

@Repository
public interface CustomerAddressJpaRepository extends JpaRepository<CustomerAddressEntity, Long> {

    /**
     * Znajdzie domyślny adres (isDefault = TRUE) dla danego klienta
     */
    Optional<CustomerAddressEntity>
    findByCustomer_CustomerIdAndIsDefaultTrue(Long customerId);

    /**
     * Sprawdzi, czy istnieje domyślny adres dla danego klienta
     */
    boolean existsByCustomer_CustomerIdAndIsDefaultTrue(Long customerId);

    /**
     * Ten sam check, ale jako ręczne zapytanie HQL.
     * (opcjonalne – wygenerowana metoda existsBy… jest wystarczająca)
     */
    @Query("""
        SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END
        FROM CustomerAddressEntity c
        WHERE c.customer.customerId = :custId
          AND c.isDefault = TRUE
        """)
    boolean existsDefaultByCustomerId(@Param("custId") Long customerId);

}
