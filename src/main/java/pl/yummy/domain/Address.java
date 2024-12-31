package pl.yummy.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "country", "city", "postalCode", "street"})
public class Address {

    Integer addressId;
    String country;
    String city;
    String postalCode;
    String street;
    Restaurant restaurant;
    AvailableDeliveryArea availableDeliveryArea;
    CustomerAddress deliveryAddress;
    BillingInformation billingInformation;
}
