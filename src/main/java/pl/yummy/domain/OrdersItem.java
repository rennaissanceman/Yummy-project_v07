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
}
