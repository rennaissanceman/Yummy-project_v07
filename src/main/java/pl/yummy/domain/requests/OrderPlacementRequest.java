package pl.yummy.domain.requests;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class OrderPlacementRequest {

//    przenosi dane niezbędne do złożenia zamówienia, np. dane klienta, adres dostawy, numer zamówienia czy
//    identyfikator restauracji
//    przenosi dane niezbędne do złożenia zamówienia (analogicznie do CarPurchaseRequest) zawierając informacje
//    o kliencie, adresie oraz identyfikatory zamówienia i restauracji.


    String existingCustomerEmail;      // jeżeli klient już istnieje, może być wykorzystany jego email
    String customerName;
    String customerSurname;
    String customerPhone;
    String customerEmail;
    String customerAddressCountry;
    String customerAddressCity;
    String customerAddressPostalCode;
    String customerAddressStreet;
    String orderNumber;                  // identyfikator zamówienia – analogicznie do carVin
    String restaurantIdentifier;         // identyfikator restauracji, np. analogicznie do salesmanPesel (identyfikacja sprzedawcy)

}
