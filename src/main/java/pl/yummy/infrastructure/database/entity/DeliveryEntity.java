package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "deliveryId")
@ToString(of = {
        "deliveryId", "deliveryNumber", "orders", "availableDeliveryArea", "courier","deliveryStatus",
        "starTime", "endTime", "estimatedDeliveryTime", "actualDeliveryDateTime","deliveryFee", "deliveryNotes"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Integer deliveryId;

    @Column(name = "delivery_number", nullable = false, unique = true)
    private String deliveryNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private OrdersEntity orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "available_delivery_area_id")
    private AvailableDeliveryAreaEntity availableDeliveryArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;

    @Column(name = "delivery_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column(name = "start_time")
    private OffsetDateTime starTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @Column(name = "estimated_delivery_time")
    private OffsetDateTime estimatedDeliveryTime;

    @Column(name = "actual_delivery_date_time")
    private OffsetDateTime actualDeliveryDateTime;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;

    @Column(name = "delivery_notes")
    private String deliveryNotes;

}
