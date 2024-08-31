package pl.yummy.infrastructure.database.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AddressEntity {

    private Integer addressId;
    private String country;
    private String postalCode;
    private String address;
}
