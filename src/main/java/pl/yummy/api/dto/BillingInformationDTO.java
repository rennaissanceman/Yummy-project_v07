package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillingInformationDTO {

//    Przenosi dane bilingowe

    Long billingInformationId;
    Long customerId;
    String companyName;
    String vatNumber;
    AddressDTO address;
}
