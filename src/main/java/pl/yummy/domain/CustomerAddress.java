package pl.yummy.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "customerAddressId")
@ToString(of = {"customerAddressId", "customer", "availableDeliveryArea", "address", "isDefault"})
public class CustomerAddress {

    Long customerAddressId;
    Customer customer;
    AvailableDeliveryArea availableDeliveryArea;
    Address address;
    Boolean isDefault;
    Set<Orders> orders;

    public boolean isDefaultAddress() {
        return Boolean.TRUE.equals(isDefault);
    }
}
