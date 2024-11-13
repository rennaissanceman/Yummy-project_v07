package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "deliveryAddressId")
@ToString(of = {"deliveryAddressId", "customer", "address"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_address")
public class _DeliveryAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_address_id")
    private Integer deliveryAddressId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private _CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private _AddressEntity address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery_address")
    private Set<_OrderEntity> orders;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery_address")
    private _DeliveryEntity delivery;
}
