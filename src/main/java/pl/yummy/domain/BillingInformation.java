package pl.yummy.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "billingInformationId")
@ToString(of = {"billingInformationId", "customer", "companyName", "vatNumber", "address"})
public class BillingInformation {

    Integer billingInformationId;
    Customer customer;
    String companyName;
    String vatNumber;
    Address address;
    Set<Invoice> invoices;

    public boolean isCompanyBilling() {
        return companyName != null && !companyName.isBlank()
                && vatNumber != null && !vatNumber.isBlank();
    }

}
