package pl.yummy.infrastructure.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "ownerId")
@ToString(of = {"ownerId", "isCompany", "vatNumber", "companyName", "name", "surname", "phone", "email"})
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

    @Column(name = "is_company")
    private Boolean isCompany;

    @Column(name = "vat_number", unique = true)
    private String vatNumber;

    @Column(name = "company_name", unique = true)
    private String companyName;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<RestaurantEntity> restaurant;
}
