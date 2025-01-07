package pl.yummy.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.yummy.business.dao.BillingInformationDAO;
import pl.yummy.infrastructure.database.entity.BillingInformationEntity;

import java.util.List;

@Repository
@AllArgsConstructor
public class BillingInformationRepository implements BillingInformationDAO {
    @Override
    public List<BillingInformationEntity> findByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<BillingInformationEntity> findByCompanyName(String companyName) {
        return null;
    }

    @Override
    public List<BillingInformationEntity> findByAddressId(Long addressId) {
        return null;
    }

    @Override
    public boolean existsByVatNumber(String vatNumber) {
        return false;
    }

    @Override
    public boolean existsByCompanyName(String companyName) {
        return false;
    }

    @Override
    public List<BillingInformationEntity> findBillingInformationByVatRange(String startVat, String endVat) {
        return null;
    }

    @Override
    public List<BillingInformationEntity> findRecentBillingInformations(int limit) {
        return null;
    }
}
