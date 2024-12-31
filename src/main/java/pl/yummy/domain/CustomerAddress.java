package pl.yummy.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "customerAddressId")
@ToString(of = {"customerAddressId", "customer", "availableDeliveryArea", "address", "isDefault"})
public class CustomerAddress {

    Integer customerAddressId;
    Customer customer;
    AvailableDeliveryArea availableDeliveryArea;
    Address address;
    Boolean isDefault;
    Set<Orders> orders;
}
