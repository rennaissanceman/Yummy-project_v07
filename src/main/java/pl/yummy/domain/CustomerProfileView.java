package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
public class CustomerProfileView {

    Integer customerId;
    String customerNumber;
    String customerName;
    String customerSurname;
    String email;
    String defaultAddress; // sformatowany adres domyślny
    List<OrderSummaryView> orderSummaries;
    String companyName; // opcjonalnie, gdy klient reprezentuje firmę
    String vatNumber;   // opcjonalnie

    @With
    @Value
    @Builder
    public static class OrderSummaryView {
        String orderNumber;
        OffsetDateTime ordersDateTime;
        String orderStatus;
    }
}

    /*
    Read Model / Value Object – agregacja danych profilu klienta.

    CustomerProfileView.
    Odczytowy model przedstawiający profil klienta – z danymi osobowymi, domyślnym adresem,
    historią zamówień oraz informacjami bilingowymi.

    Przykładowe pola:

    - customerNumber, customerName, customerSurname, email
    - domyślny adres (może być obiektem typu CustomerAddress)
    - lista zamówień (skrócony podgląd, np. numer zamówienia, data, status)
    - informacje bilingowe (np. companyName, vatNumber, adres billingowy)
    */
