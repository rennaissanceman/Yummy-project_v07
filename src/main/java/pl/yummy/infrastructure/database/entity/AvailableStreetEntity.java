package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "availableStreetId")
@ToString(of = {"availableStreetId", "availableStreetName", "city", "postalCode"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "available_street")
public class AvailableStreetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_street_id")
    private Integer availableStreetId;

    @Column(name = "available_street_name")
    private String availableStreetName;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "availableStreet")
    private Set<DeliveryAreaEntity> deliveryAreas;



/*    public void addServiceRequest(CarServiceRequestEntity carServiceRequestEntity) {
        if (Objects.isNull(carServiceRequests)) {
            this.carServiceRequests = new HashSet<>();
        }
        carServiceRequests.add(carServiceRequestEntity);
    }*/
}
