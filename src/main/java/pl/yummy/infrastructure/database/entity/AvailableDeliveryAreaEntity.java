package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "availableDeliveryAreaId")
@ToString(of = {"availableDeliveryArea", "restaurant", "address"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "available_delivery_area")
public class AvailableDeliveryAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_delivery_area_id")
    private Integer availableDeliveryArea;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "available_delivery_area", cascade = CascadeType.ALL)
    private Set<CustomerAddressEntity> customerAddresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "available_delivery_area", cascade = CascadeType.ALL)
    private Set<OrderEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "available_delivery_area", cascade = CascadeType.ALL)
    private Set<DeliveryEntity> deliveries;
}
