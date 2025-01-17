package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "orderHistoryId")
@ToString(of = {"orderHistoryId", "ordersNumber", "customerName", "restaurantName", "menuName", "orderStatus",
        "deliveryStatus", "paymentStatus", "totalAmount", "orderDate", "deliveryDate"})
public class YummyOrderHistory {

    Integer orderHistoryId; // Unikalny identyfikator rekordu historii zamówień
    String ordersNumber; // Numer zamówienia
    String customerName; // Nazwa klienta (lub jego pełne imię i nazwisko)
    String restaurantName; // Nazwa restauracji
    String menuName; // Nazwa menu, z którego dokonano zamówienia
    OrdersStatusEnumDomain orderStatus; // Status zamówienia
    DeliveryStatusEnumDomain deliveryStatus; // Status dostawy
    PaymentStatusEnumDomain paymentStatus; // Status płatności
    BigDecimal totalAmount; // Łączna kwota zamówienia
    OffsetDateTime orderDate; // Data i czas złożenia zamówienia
    OffsetDateTime deliveryDate; // Data i czas dostawy zamówienia (jeśli dostarczono)

    public boolean isDelivered() {
        return deliveryStatus == DeliveryStatusEnumDomain.DELIVERED;
    }

    public boolean isPaid() {
        return paymentStatus == PaymentStatusEnumDomain.COMPLETED;
    }
}
