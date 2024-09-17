package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;

public class zOrderItemEntity {

    private Integer orderItemId;
    private zOrderEntity order;
    private zMenuItemEntity menuItem;
    private String itemName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String itemNotes;

}
