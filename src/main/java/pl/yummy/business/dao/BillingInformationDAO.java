package pl.yummy.business.dao;

import pl.yummy.domain.BillingInformation;
import pl.yummy.domain.Customer;
import pl.yummy.infrastructure.database.entity.BillingInformationEntity;

import java.util.List;
import java.util.Optional;

public interface BillingInformationDAO {

    List<BillingInformation> findByVatNumber(String vatNumber);

    List<BillingInformationEntity> findByCompanyName(String companyName);

    Optional<BillingInformation> findByCustomerId(Integer customerId);

    boolean existsByCompanyName(String companyName);


    Optional<BillingInformation> findByCustomer(Customer customer);
    List<BillingInformation> findByCompanyNameContainingIgnoreCase(String companyName);


}
