package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "deliveryId")
@ToString(of = {
        "deliveryId", "deliveryNumber", "order", "courier", "deliveryAddress","deliveryStartDateTime",
        "estimatedDeliveryTime", "deliveryStatus", "deliveryNotes", "deliveryTime","deliveryFee"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery")
public class DeliveryEntity {


    @Column(name = "delivery_id")
    private Integer deliveryId;

    @Column(name = "delivery_number", nullable = false, unique = true)
    private String deliveryNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddressEntity deliveryAddress;

    @Column(name = "delivery_start_date_time")
    private OffsetDateTime deliveryStartDateTime;

    @Column(name = "estimated_delivery_time")
    private OffsetDateTime estimatedDeliveryTime;

    @Column(name = "actual_delivery_date_time")
    private OffsetDateTime actualDeliveryDateTime;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "delivery_notes")
    private String deliveryNotes;

    @Column(name = "delivery_time")
    private OffsetDateTime deliveryTime;

    @Column(name = "delivery_fee")
    private BigDecimal deliveryFee;
}
