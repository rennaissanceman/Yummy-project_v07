package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "deliveryAddressId")
@ToString(of = {"deliveryAddressId", "customer", "address"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deliveryAddress")
public class DeliveryAddressEntity {

    private Integer deliveryAddressId;
    private CustomerEntity customer;
    private AddressEntity address;
}
