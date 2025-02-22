package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressDTO {

//  Dla adresów klienta

    Long customerAddressId;
    Boolean isDefault;
    AddressDTO address;
    Long availableDeliveryAreaId;   //(opcjonalnie)
}
