package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "billingInformationId")
@ToString(of =
        {"billingInformationId", "customer", "companyName", "vatNumber", "companyAddress", "city", "postalCode", "country"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "billing_information")
public class BillingInformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_information_id")
    private Integer billingInformationId;


    @Column(name = "company_name", unique = true)
    private String companyName;

    @Column(name = "vat_number", unique = true)
    private String vatNumber;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "billingInformation")
    private Set<InvoiceEntity> invoices;

}
