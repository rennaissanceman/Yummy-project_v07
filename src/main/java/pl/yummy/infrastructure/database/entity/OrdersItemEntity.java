package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = "ordersItemId")
@ToString(of = {"ordersItemId", "orders", "menuItem", "itemName", "quantity", "unitPrice", "totalPrice", "itemNotes"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_item")
public class OrdersItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_item_id")
    private Integer ordersItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private OrdersEntity orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItemEntity menuItem;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "item_notes")
    private String itemNotes;


}
