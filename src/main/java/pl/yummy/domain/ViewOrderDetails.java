package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@With
@Value
@Builder
public class ViewOrderDetails {

    String orderNumber;
    OffsetDateTime ordersDateTime;
    String customerName;
    String customerEmail;
    String customerPhone;
    List<OrderItemView> orderItems;
    String orderStatus;
    String paymentStatus;
    String deliveryStatus;
    BigDecimal totalAmount;
    String deliveryAddress; // sformatowany adres dostawy

    @With
    @Value
    @Builder
    public static class OrderItemView {
        String itemName;
        Integer quantity;
        BigDecimal unitPrice;
        BigDecimal totalPrice;
    }
}

    /*
    Read Model / Value Object – pełen podgląd szczegółów zamówienia.

    OrderDetailsView.
    Model odczytu prezentujący szczegółowe informacje o zamówieniu. Może agregować dane z różnych części domeny,
    takie jak lista pozycji zamówienia, status płatności, status dostawy, łączna kwota zamówienia oraz dane adresowe.

    Przykładowe pola:

    - orderNumber, ordersDateTime
    - customerName oraz dane kontaktowe klienta
    - lista pozycji (z nazwami, ilościami, cenami)
    - status zamówienia, status płatności, status dostawy
    - łączna kwota, adres dostawy
    */
