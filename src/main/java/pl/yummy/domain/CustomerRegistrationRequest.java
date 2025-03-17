package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class CustomerRegistrationRequest {

    String customerName;
    String customerSurname;
    String email;
    String phone;
    Boolean isCompany;
    String companyName; // Opcjonalne – tylko dla firm
    String country;
    String city;
    String postalCode;
    String street;
    String password; // np. do utworzenia konta
}



    /*
    Request/Command DTO – do rejestracji nowego klienta.

    CustomerRegistrationRequest.
    Używana przy rejestracji nowego klienta. Może zawierać dane osobowe, dane kontaktowe,
    informacje dotyczące firmy (jeżeli klient reprezentuje firmę), a także adres oraz ewentualnie
    dane do logowania (np. pola do utworzenia UserAuth).

    Przykładowe pola:

    - customerName, customerSurname
    - email, phone
    - isCompany, companyName (opcjonalnie)
    - dane adresowe (np. country, city, postalCode, street)
    - ewentualnie hasło lub inne dane logowania
    */
