package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "courierId")
@ToString(of = {
        "courierId", "courierNumber", "contactDetails", "userLogData", "vehicleType", "vehicleRegistrationNumber",
        "averageRatings", "deliveryCount", "hireDate"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courier")
public class _CourierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private Integer courierId;

    @Column(name = "courier_number", nullable = false, unique = true)
    private String courierNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_details_id")
    private _ContactDetailsEntity contactDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_log_data_id")
    private _UserLogDataEntity userLogData;

    @Column(name = "vehicleType", nullable = false)
    private String vehicleType;

    @Column(name = "vehicle_registration_number", nullable = false, unique = true)
    private String vehicleRegistrationNumber;

    @Column(name = "average_ratings", nullable = false)
    private Double averageRatings;

    @Column(name = "delivery_count", nullable = false)
    private Integer deliveryCount;

    @Column(name = "hire_date", nullable = false)
    private OffsetTime hireDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier")
    private Set<_DeliveryEntity> deliveries;

}
