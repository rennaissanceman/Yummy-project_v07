package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.CourierStatusEnumEntity;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "courierId")
@ToString(of = {
        "courierId", "courierNumber", "courierName", "courierSurname", "userAuth", "vehicleType",
        "vehicleRegistrationNumber", "averageRatings", "deliveryCount", "hireDate"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courier")
public class CourierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private Integer courierId;

    @Column(name = "courier_number", nullable = false, unique = true)
    private String courierNumber;

    @Column(name = "courier_name", nullable = false)
    private String courierName;

    @Column(name = "courier_surname", nullable = false)
    private String courierSurname;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_auth_id")
    private UserAuthEntity userAuth;

    @Column(name = "vehicleType", nullable = false)
    private String vehicleType;

    @Column(name = "vehicle_registration_number", nullable = false, unique = true)
    private String vehicleRegistrationNumber;

    @Column(name = "average_ratings", nullable = false)
    private Double averageRatings;

    @Column(name = "delivery_count", nullable = false)
    private Integer deliveryCount;

    @Column(name = "courier_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CourierStatusEnumEntity courierStatus;

    @Column(name = "hire_date", nullable = false)
    private OffsetDateTime hireDate;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier")
    private Set<DeliveryEntity> deliveries;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "courier")
    private Set<FeedbackEntity> feedbacks;


}
