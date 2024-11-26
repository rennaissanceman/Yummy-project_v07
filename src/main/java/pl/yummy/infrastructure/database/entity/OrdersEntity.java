package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "orderId")
@ToString(of = {"orderId", "orderNumber", "customer", "menu", "orderDateTime", "orderStatus", "orderDescription",
        "totalAmount", "availableDeliveryAreaId", "customerAddressId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrdersEntity {


    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    @Column(name = "order_date_time", nullable = false)
    private OffsetDateTime orderDateTime;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_description")
    private String orderDescription;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "available_delivery_area_id")
    private AvailableDeliveryAreaEntity availableDeliveryAreaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerAddressId")
    private CustomerAddressEntity customerAddressId;


    @OneToOne(fetch = FetchType.EAGER, mappedBy = "orders", cascade = CascadeType.ALL)
    private PaymentEntity payment;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orders")
    private InvoiceEntity invoice;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orders")
    private ReceiptEntity receipt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.ALL)
    private DeliveryEntity delivery;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<OrderItemEntity> orderItems;


}
