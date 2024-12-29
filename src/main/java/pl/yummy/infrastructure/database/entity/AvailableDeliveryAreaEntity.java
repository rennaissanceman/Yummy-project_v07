package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "availableDeliveryAreaId")
@ToString(of = {"availableDeliveryAreaId", "restaurant", "address"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "available_delivery_area")
public class AvailableDeliveryAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_delivery_area_id")
    private Integer availableDeliveryAreaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "available_delivery_area")
    private Set<CustomerAddressEntity> customerAddresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "available_delivery_area")
    private Set<OrdersEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "available_delivery_area")
    private Set<DeliveryEntity> deliveries;
}
