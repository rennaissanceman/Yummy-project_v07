package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatusEnumEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "ordersId")
@ToString(of = {"ordersId", "ordersNumber", "customer", "menu", "ordersDateTime", "ordersStatus", "ordersDescription",
        "totalAmount", "ordersRating", "availableDeliveryAreaId", "customerAddressId"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrdersEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Integer ordersId;


    @Column(name = "orders_number", nullable = false, unique = true)
    private String ordersNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

    @Column(name = "orders_date_time", nullable = false)
    private OffsetDateTime ordersDateTime;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrdersStatusEnumEntity ordersStatus;

    @Column(name = "orders_description")
    private String ordersDescription;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "orders_rating")
    private Integer ordersRating;

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
    private Set<OrdersItemEntity> ordersItems;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
    private Set<FeedbackEntity> feedbacks;

}
