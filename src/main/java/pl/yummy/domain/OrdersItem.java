package pl.yummy.domain;

import lombok.*;

import java.math.BigDecimal;

@With
@Value
@Builder
@EqualsAndHashCode(of = "ordersItemId")
@ToString(of = {"ordersItemId", "orders", "menuItem", "itemName", "quantity", "unitPrice", "totalPrice", "itemNotes"})
public class OrdersItem {

    Integer ordersItemId;
    Orders orders;
    MenuItem menuItem;
    String itemName;
    Integer quantity;
    BigDecimal unitPrice;
    BigDecimal totalPrice;
    String itemNotes;

    public boolean isValid() {
        return quantity != null && quantity > 0
                && unitPrice != null && unitPrice.compareTo(BigDecimal.ZERO) > 0
                && totalPrice != null && totalPrice.compareTo(BigDecimal.ZERO) > 0;
    }
}
