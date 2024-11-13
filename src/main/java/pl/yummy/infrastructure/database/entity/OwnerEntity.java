package pl.yummy.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "ownerId")
@ToString(of = {"ownerId", "contactDetails", "userLogData"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Integer ownerId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_details_id")
    private ContactDetailsEntity contactDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_log_data_id")
    private UserLogDataEntity userLogData;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<RestaurantEntity> restaurants;
}
