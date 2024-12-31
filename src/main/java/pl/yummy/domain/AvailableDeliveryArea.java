package pl.yummy.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "availableDeliveryAreaId")
@ToString(of = {"availableDeliveryAreaId", "restaurant", "address"})
public class AvailableDeliveryArea {

    Integer availableDeliveryAreaId;
    Restaurant restaurant;
    Address address;
    Set<CustomerAddress> customerAddresses;
    Set<Orders> orders;
    Set<Delivery> deliveries;
}
