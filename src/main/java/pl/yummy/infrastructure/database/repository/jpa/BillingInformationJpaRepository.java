package pl.yummy.infrastructure.database.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.yummy.infrastructure.database.entity.BillingInformationEntity;

import java.util.List;

@Repository
public interface BillingInformationJpaRepository extends JpaRepository<BillingInformationEntity, Long> {

    // Niestandardowe metody (Spring Data JPA generuje zapytania na podstawie nazwy metody)
    List<BillingInformationEntity> findByCustomerId(Long customerId);
    List<BillingInformationEntity> findByCompanyName(String companyName);
    List<BillingInformationEntity> findByAddressId(Long addressId);
    boolean existsByVatNumber(String vatNumber);
    boolean existsByCompanyName(String companyName);

    // Zapytanie niestandardowe dla zakresu VAT
    @Query("SELECT b FROM BillingInformationEntity b WHERE b.vatNumber BETWEEN :startVat AND :endVat")
    List<BillingInformationEntity> findBillingInformationByVatRange(@Param("startVat") String startVat, @Param("endVat") String endVat);

    // Zapytanie niestandardowe dla najnowszych informacji
    @Query("SELECT b FROM BillingInformationEntity b ORDER BY b.creationDate DESC")
    List<BillingInformationEntity> findRecentBillingInformations(@Param("limit") int limit);
}
