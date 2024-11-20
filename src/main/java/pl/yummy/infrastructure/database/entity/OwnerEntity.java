package pl.yummy.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "ownerId")
@ToString(of = {"ownerId", "ownerNumber", "userAuth"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id", unique = true, nullable = false)
    private Integer ownerId;

    @Column(name = "owner_number", unique = true, nullable = false)
    private String ownerNumber;

    @Column(name = "owner_name", unique = true, nullable = false)
    private String ownerName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_auth_id")
    private UserAuthEntity userAuth;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<RestaurantEntity> restaurants;


}
