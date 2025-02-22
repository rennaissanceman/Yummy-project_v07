package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

//    Dla pozycji zamówienia
    Long ordersItemId;
    Long menuItemId;
    String itemName;
    Integer quantity;
    BigDecimal unitPrice;
    BigDecimal totalPrice;
    String itemNotes;
}
