package pl.yummy.domain.requests;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import pl.yummy.domain.AvailableDeliveryArea;
import pl.yummy.domain.Customer;
import pl.yummy.domain.CustomerAddress;
import pl.yummy.domain.Menu;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@With
@Value
@Builder
public class OrderRequest {

    //    DTO do składania zamówienia – zawiera tylko niezbędne dane

    String ordersNumber;  // Może być generowany
    Customer customer;
    Menu menu;
    OffsetDateTime ordersDateTime;
    String ordersDescription;
    BigDecimal totalAmount;
    AvailableDeliveryArea availableDeliveryArea;
    CustomerAddress customerAddress;
    Set<OrderItemRequest> ordersItems;

    @With
    @Value
    @Builder
    public static class OrderItemRequest {
        Long menuItemId;
        Integer quantity;
        BigDecimal unitPrice;
        BigDecimal totalPrice;
        String itemNotes;
    }
}
