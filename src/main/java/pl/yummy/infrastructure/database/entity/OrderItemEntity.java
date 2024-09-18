package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;

public class OrderItemEntity {

    private Integer orderItemId;
    private OrderEntity order;
    private FoodEntity food;
    private String itemName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String itemNotes;


}
