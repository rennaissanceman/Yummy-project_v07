package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
public class OrderRequest {

    //    DTO do składania zamówienia – zawiera tylko niezbędne dane

    Integer ordersId;               // Opcjonalny – ID zamówienia, jeśli istnieje (np. przy aktualizacji)
    String ordersNumber;            // Numer zamówienia
    Customer customer;              // Dane klienta
    Menu menu;                      // Menu wybrane przez klienta
    OffsetDateTime ordersDateTime;  // Data i czas zamówienia
    String ordersDescription;       // Opis zamówienia (opcjonalnie)
    BigDecimal totalAmount;         // Całkowita kwota zamówienia
    AvailableDeliveryArea availableDeliveryArea; // Obszar dostawy
    CustomerAddress customerAddress;             // Adres klienta
    Set<OrderItemRequest> ordersItems;           // Lista pozycji zamówienia

    @With
    @Value
    @Builder
    public static class OrderItemRequest {
        Integer ordersItemId; // Opcjonalnie – ID pozycji, gdy aktualizujemy istniejącą
        Long menuItemId;      // ID pozycji menu
        Integer quantity;     // Ilość
        BigDecimal unitPrice; // Cena jednostkowa
        BigDecimal totalPrice;// Łączna cena za tę pozycję
        String itemNotes;     // Uwagi do pozycji
    }

    public boolean isValidOrder() {
        return customer != null
                && availableDeliveryArea != null
                && customerAddress != null
                && ordersItems != null && !ordersItems.isEmpty()
                && totalAmount != null && totalAmount.compareTo(BigDecimal.ZERO) > 0;
    }
}
