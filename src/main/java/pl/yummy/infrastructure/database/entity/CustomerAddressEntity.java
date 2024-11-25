package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "customerAddressId")
@ToString(of = {"customerAddressId", "customer", "availableDeliveryArea", "address", "isDefault"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_address")
public class CustomerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_address_id")
    private Integer customerAddressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "available_delivery_area_id")
    private AvailableDeliveryAreaEntity availableDeliveryArea;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer_address")
    private Set<OrdersEntity> orders;
}
