package pl.yummy.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "availableDeliveryAreaId")
@ToString(of = {"availableDeliveryAreaId", "restaurant", "address"})
public class AvailableDeliveryArea {

    Long availableDeliveryAreaId;
    Restaurant restaurant;
    Address address;
    Set<CustomerAddress> customerAddresses;
    Set<Orders> orders;
    Set<Delivery> deliveries;

    public boolean shouldBeEnabledForDelivery() {
        return restaurant != null
                && address != null
                && customerAddresses != null && !customerAddresses.isEmpty()
                && orders != null && !orders.isEmpty()
                && deliveries != null && !deliveries.isEmpty();
    }

    public boolean hasOrders() {
        return orders != null && !orders.isEmpty();
    }

    public boolean hasDeliveries() {
        return deliveries != null && !deliveries.isEmpty();
    }
}
