package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationRequestDTO {

    private String customerName;
    private String customerSurname;
    private String email;
    private String password;
    private String phone;

    public static CustomerRegistrationRequestDTO buildDefault() {
        return CustomerRegistrationRequestDTO.builder()
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
