package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.util.List;

@With
@Value
@Builder
public class ToDoOrderSummary {

//    Cel: Podsumowanie zamówienia do wyświetlenia w UI (np. przed potwierdzeniem zamówienia).

//    Umożliwia wyświetlenie klientowi podsumowania przed ostatecznym zatwierdzeniem zamówienia.

    String customerName;
    String restaurantName;
    List<MenuItem> menuItems;
    BigDecimal totalAmount;
    String estimatedDeliveryTime;
}
