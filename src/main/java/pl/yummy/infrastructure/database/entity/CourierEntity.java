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
public class CourierEntity {


    @Column(name = "courierId")
    private Integer courierId;

    @Column(name = "courierNumber", nullable = false, unique = true)
    private String courierNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_details_id")
    private ContactDetailsEntity contactDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_log_data_id")
    private UserLogDataEntity userLogData;

    @Column(name = "vehicleType", nullable = false)
    private String vehicleType;

    @Column(name = "vehicleRegistrationNumber", nullable = false, unique = true)
    private String vehicleRegistrationNumber;

    @Column(name = "averageRatings", nullable = false)
    private Double averageRatings;

    @Column(name = "deliveryCount", nullable = false)
    private Integer deliveryCount;

    @Column(name = "hireDate", nullable = false)
    private OffsetTime hireDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier")
    private Set<DeliveryEntity> deliveries;

}
