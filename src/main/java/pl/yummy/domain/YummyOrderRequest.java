package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "ordersId")
@ToString(of = {"ordersId", "ordersNumber", "customer", "menu", "ordersDateTime", "ordersStatus", "ordersDescription",
        "totalAmount", "availableDeliveryArea", "customerAddress", "ordersItems"})
public class YummyOrderRequest {

    Integer ordersId; // ID zamówienia
    String ordersNumber; // Unikalny numer zamówienia
    Customer customer; // Klient składający zamówienie
    Menu menu; // Menu, z którego wybrano pozycje
    OffsetDateTime ordersDateTime; // Data i czas zamówienia
    OrdersStatusEnumDomain ordersStatus; // Status zamówienia
    String ordersDescription; // Opcjonalny opis zamówienia
    BigDecimal totalAmount; // Całkowita kwota zamówienia
    AvailableDeliveryArea availableDeliveryArea; // Obszar dostawy
    CustomerAddress customerAddress; // Adres klienta
    Set<OrderItem> ordersItems; // Pozycje w zamówieniu


    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "ordersItemId")
    @ToString(of = {"ordersItemId", "menuItem", "quantity", "unitPrice", "totalPrice", "itemNotes"})
    public static class OrderItem {
        Integer ordersItemId; // ID pozycji zamówienia
        MenuItem menuItem; // Pozycja menu
        Integer quantity; // Ilość pozycji
        BigDecimal unitPrice; // Cena jednostkowa
        BigDecimal totalPrice; // Łączna cena za pozycję
        String itemNotes; // Uwagi do pozycji
    }

    public boolean isValidOrder() {
        return customer != null
                && availableDeliveryArea != null
                && customerAddress != null
                && ordersItems != null && !ordersItems.isEmpty()
                && totalAmount != null && totalAmount.compareTo(BigDecimal.ZERO) > 0;
    }
}
