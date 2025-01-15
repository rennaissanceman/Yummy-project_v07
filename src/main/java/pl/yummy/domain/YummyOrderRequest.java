package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class YummyOrderRequest {

//    klasa dotycząca żądania złożenia zamówienia

    // Dane klienta
    String customerName;
    String customerSurname;
    String customerPhone;
    String customerEmail;

    // Adres dostawy - dane z encji AddressEntity
    String deliveryCountry;
    String deliveryCity;
    String deliveryPostalCode;
    String deliveryStreet;

    // Szczegóły zamówienia - z encji MenuItemEntity
    String menuItemName;
    Integer quantity;
    String specialInstructions;

    // Dane restauracji - dane z encji RestaurantEntity
    String restaurantName;
    String restaurantPhone;
    String restaurantEmail;

    // Dane dostawcy - dane z encji CourierEntity
    String courierName;
    String courierSurname;
    String courierVehicleType;

    // Szczegóły płatności - dane z encji PaymentEntity i PaymentMethodEntity
    String paymentMethodName;
    String paymentStatus;
    Double totalAmount;

}
