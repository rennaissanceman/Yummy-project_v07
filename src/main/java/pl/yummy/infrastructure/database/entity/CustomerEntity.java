package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = "customerId")
@ToString(of = {"customerId", "customerNumber", "isCompany", "contactDetails", "wantInvoice", "userLogData"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "customer_number", nullable = false, unique = true)
    private String customerNumber;

    @Column(name = "is_company", nullable = false)
    private Boolean isCompany;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_details_id")
    private ContactDetailsEntity contactDetails;

    @Column(name = "want_invoice", nullable = false)
    private Boolean wantInvoice;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_log_data_id")
    private UserLogDataEntity userLogData;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer")
    private BillingInformationEntity billingInformation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<DeliveryAddressEntity> deliveryAddresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<OrderEntity> orders;

}
