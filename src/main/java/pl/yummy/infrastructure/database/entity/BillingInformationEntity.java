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

    private CustomerEntity customer;
    private String companyName;
    private String vatNumber;
    private AddressEntity address;








}
