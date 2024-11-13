package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.ContactType;

@Getter
@Setter
@EqualsAndHashCode(of = "contactDetailsId")
@ToString(of = {"contactDetailsId", "contactType", "corporateName", "name", "surname", "phone", "email"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact_details")
public class _ContactDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_details_id")
    private Integer contactDetailsId;

    @Column(name = "contact_type")
    private ContactType contactType;

    @Column(name = "corporate_name", unique = true)
    private String corporateName;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contact_details")
    private _OwnerEntity owner;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contact_details")
    private _CourierEntity courier;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contact_details")
    private _CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "contact_details")
    private _RestaurantEntity restaurant;
}
