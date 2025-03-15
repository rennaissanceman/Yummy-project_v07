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
public class RequestCustomerUpdateDTO {

    // Unikalny numer klienta, który identyfikuje klienta w systemie.
    private String customerNumber;

    // Podstawowe dane osobowe.
    private String customerName;
    private String customerSurname;

    // Dane kontaktowe.
    private String email;
    private String phone;

    // Opcjonalne dane.
    private Boolean isCompany;
    private String companyName;

    // Dane bilingowe (opcjonalne).
    private BillingInformationDTO billingInformation;

    // Lista adresów klienta (opcjonalnie, np. adres domowy, adres do wysyłki).
    private List<CustomerAddressDTO> customerAddressDTOList;
}
