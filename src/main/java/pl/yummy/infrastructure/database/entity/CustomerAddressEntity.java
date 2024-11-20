package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "customerAddressId")
@ToString(of = {"customerAddressId", "customerId", "availableDeliveryAreaId", "isDefault"})
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "available_delivery_area_id")
    private AvailableDeliveryAreaEntity availableDeliveryAreaId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity addressId;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer_address", cascade = CascadeType.ALL)
    private Set<OrderEntity> orders;
}
