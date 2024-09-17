package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "addressId")
@ToString(of = {"addressId", "country", "city", "postalCode", "street", "streetNumber"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class zAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private String streetNumber;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private zCustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private zOrderEntity order;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private zRestaurantEntity restaurant;


}