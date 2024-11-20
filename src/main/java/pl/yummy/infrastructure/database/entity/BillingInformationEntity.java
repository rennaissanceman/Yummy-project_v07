package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "billingInformationId")
@ToString(of = {"billingInformationId", "customer", "companyName", "vatNumber", "address"})
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(name = "vat_number", nullable = false, unique = true)
    private String vatNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "billing_information", cascade = CascadeType.ALL)
    private Set<InvoiceEntity> invoices;










}
