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
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Integer addressId;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street_number", nullable = false)
    private String streetNumber;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private RestaurantEntity restaurant;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private AvailableDeliveryAreaEntity availableDeliveryArea;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
    private CustomerAddressEntity deliveryAddress;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
    private BillingInformationEntity billingInformation;
}
