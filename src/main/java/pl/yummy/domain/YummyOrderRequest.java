package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.OffsetDateTime;

@With
@Value
@Builder
public class YummyOrderRequest {

    // Dane klienta
    String customerName;
    String customerSurname;
    String customerEmail;
    String customerPhone;

    // Adres dostawy
    String deliveryAddressStreet;
    String deliveryAddressCity;
    String deliveryAddressPostalCode;
    String deliveryAddressCountry;

    // Szczegóły zamówienia
    String restaurantName;
    String menuItemName;
    Integer quantity;
    String paymentMethod;

    // Dodatkowe informacje
    String orderNote; // Opcjonalna uwaga klienta (np. "bez sosu", "bez glutenu")
    OffsetDateTime orderDate; // Czas złożenia zamówienia
    Boolean isExpressDelivery; // Flaga wskazująca na szybką dostawę
}
