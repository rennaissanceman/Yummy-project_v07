package pl.yummy.domain;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Value
@Builder
@ToString(of = "customerId")
public class YummyOrderHistory {

    String customerId;
    List<OrderDetail> orderDetails;

    @Value
    @Builder
    @ToString(of = {"orderId", "orderDate", "deliveryDate", "totalAmount", "status"})
    public static class OrderDetail {
        String orderId; // Identyfikator zamówienia
        OffsetDateTime orderDate; // Data złożenia zamówienia
        OffsetDateTime deliveryDate; // Data dostarczenia zamówienia
        String restaurantName; // Nazwa restauracji
        List<MenuItem> menuItems; // Lista zamówionych pozycji
        BigDecimal totalAmount; // Całkowita kwota zamówienia
        String status; // Status zamówienia, np. "DELIVERED", "IN_PROGRESS"
        String customerNote; // Uwagi klienta dotyczące zamówienia
        String paymentMethod; // Metoda płatności, np. "CARD", "CASH"
        DeliveryInfo deliveryInfo; // Szczegóły dostawy
        Courier courier; // Informacje o kurierze

        @Value
        @Builder
        @ToString(of = {"itemName", "quantity", "unitPrice", "totalPrice"})
        public static class MenuItem {
            String itemName; // Nazwa pozycji
            Integer quantity; // Ilość
            BigDecimal unitPrice; // Cena jednostkowa
            BigDecimal totalPrice; // Całkowita cena
        }

        @Value
        @Builder
        @ToString
        public static class DeliveryInfo {
            String deliveryAddress; // Adres dostawy
            OffsetDateTime estimatedDeliveryTime; // Szacowany czas dostawy
            String deliveryInstructions; // Specjalne instrukcje dla dostawy
        }

        @Value
        @Builder
        @ToString(of = "courierId")
        public static class Courier {
            String courierId; // Identyfikator kuriera
            String courierName; // Imię i nazwisko kuriera
            String courierPhone; // Numer telefonu kuriera
        }
    }
}
