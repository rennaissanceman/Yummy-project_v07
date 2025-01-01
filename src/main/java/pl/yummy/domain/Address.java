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

    public boolean isComplete() {
        return country != null && !country.isBlank()
                && city != null && !city.isBlank()
                && postalCode != null && !postalCode.isBlank()
                && street != null && !street.isBlank();
    }

}
