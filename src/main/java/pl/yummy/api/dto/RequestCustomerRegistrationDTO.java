package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCustomerRegistrationDTO {

    private String customerName;
    private String customerSurname;
    private String email;
    private String password;
    private String phone;

    public static RequestCustomerRegistrationDTO buildDefault() {
        return RequestCustomerRegistrationDTO.builder()
                .customerName("")
                .customerSurname("")
                .email("")
                .password("")
                .phone("")
                .build();
    }

    // Opcjonalnie możesz dodać inne pola, np.:
    // private Boolean isCompany;
    // private String companyName;

    /*
    (lub użyć bezpośrednio istniejącego CustomerRegistrationRequest) – zawiera dane rejestracyjne klienta.
    */
}
