package pl.yummy.business.dao;

import pl.yummy.domain.AvailableDeliveryArea;
import pl.yummy.domain.Customer;
import pl.yummy.domain.CustomerAddress;

import java.util.List;
import java.util.Optional;

public interface CustomerAddressDAO {

    List<CustomerAddress> findByCustomerId(Integer customerId);
    Optional<CustomerAddress> findByAddressIdAndIsDefaultTrue(Integer addressId);

    List<CustomerAddress> findByCustomer(Customer customer);
    List<CustomerAddress> findByIsDefault(Boolean isDefault);
    List<CustomerAddress> findByAvailableDeliveryArea(AvailableDeliveryArea deliveryArea);


    List<CustomerAddress> findByDefaultStatus(Boolean isDefault);

    List<CustomerAddress> findByDeliveryArea(AvailableDeliveryArea deliveryArea);
}
