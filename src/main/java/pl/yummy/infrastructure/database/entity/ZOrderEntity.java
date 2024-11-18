package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "orderId")
@ToString(of = {"orderId", "orderNumber", "customer", "menu", "orderDateTime", "orderStatus", "orderDescription",
        "totalAmount", "restaurantAvailableStreet"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class ZOrderEntity {


    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private ZCustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id")
    private ZMenuEntity menu;

    @Column(name = "order_date_time", nullable = false)
    private OffsetDateTime orderDateTime;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "order_description")
    private String orderDescription;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_available_id")
    private ZRestaurantAvailableStreetEntity restaurantAvailableStreet;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order_id")
    private ZInvoiceEntity invoice;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order_id")
    private ZReceiptEntity receipt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order_id")
    private ZDeliveryEntity delivery;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order_id")
    private Set<ZOrderItemEntity> orderItems;


}
