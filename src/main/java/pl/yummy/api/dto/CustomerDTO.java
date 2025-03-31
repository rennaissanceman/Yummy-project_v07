package pl.yummy.api.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    // Dane ogólne klienta
    Long customerId;
    String customerNumber;
    Boolean isCompany;
    String companyName;
    String customerName;
    String customerSurname;
    String email;
    BillingInformationDTO billingInformation;
    List<CustomerAddressDTO> customerAddressDTOList;

    // Dane widoku profilu klienta
    String defaultAddress; // np. sformatowany domyślny adres
    String phone;          // numer telefonu
    List<OrderSummaryView> orderSummaries; // skrócony podgląd ostatnich zamówień
    String vatNumber;      // numer VAT (dla firm)

    @With
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderSummaryView {
        String orderNumber;
        OffsetDateTime ordersDateTime;
        String orderStatus;
    }
}
