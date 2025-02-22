package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

//    Reprezentacja klienta z najważniejszymi danymi

    Long customerId;
    String customerNumber;
    Boolean isCompany;
    String companyName; //(opcjonalnie)
    String customerName;
    String customerSurname;
    String email;

    BillingInformationDTO billingInformation; //(opcjonalnie)
    List<CustomerAddressDTO> customerAddressDTOList; //(opcjonalnie) lista uproszczonych adresów – np.
}
