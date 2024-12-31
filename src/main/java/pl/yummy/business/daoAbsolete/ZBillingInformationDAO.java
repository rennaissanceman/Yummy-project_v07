package pl.yummy.business.daoAbsolete;

import pl.yummy.infrastructure.database.entity.AddressEntity;
import pl.yummy.infrastructure.database.entity.BillingInformationEntity;

import java.util.List;
import java.util.Optional;

public interface ZBillingInformationDAO {

    /* CRUD */

    /* create */
    BillingInformationEntity createBillingInformation(BillingInformationEntity billingInformation);
    List<BillingInformationEntity> findBillingInformationByCustomerId(Long customerId);

    /* read */
    Optional<BillingInformationEntity> findBillingInformationById(Long billingInformationId);
    List<BillingInformationEntity> findBillingInformationByCompanyName(String companyName);
    List<BillingInformationEntity> findBillingInformationByAddressId(Long addressId);
    List<BillingInformationEntity> findBillingInformationByVatRange(String startVat, String endVat);
    List<BillingInformationEntity> findRecentBillingInformations(int limit);
    List<BillingInformationEntity> findAllBillingInformations();

    /* update */
    BillingInformationEntity updateBillingInformation(BillingInformationEntity billingInformation);
    BillingInformationEntity updateBillingAddress(Long billingInformationId, AddressEntity newAddress);

    /* delete */
    void deleteBillingInformation(Long billingInformationId);
    void deleteBillingInformationsByCustomerId(Long customerId);




    boolean existsBillingInformationByVatNumber(String vatNumber);
    boolean existsBillingInformationByCompanyName(String companyName);
    long countBillingInformations();



}
