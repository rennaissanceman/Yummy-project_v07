package pl.yummy.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "customerId")
@ToString(of = {"customerId", "customerNumber", "isCompany", "companyName", "customerName", "customerSurname", "userAuth"})
public class Customer {

    Integer customerId;
    String customerNumber;
    Boolean isCompany;
    String companyName;
    String customerName;
    String customerSurname;
    UserAuth userAuth;
    BillingInformation billingInformation;
    Set<CustomerAddress> customerAddresses;
    Set<Orders> orders;
}
