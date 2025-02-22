package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

//    Zawiera podstawowe dane adresowe

    Long addressId;
    String country;
    String city;
    String postalCode;
    String street;
}
