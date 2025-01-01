package pl.yummy.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Objects;
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

    public Set<CustomerAddress> getCustomerAddress() {
        return Objects.isNull(customerAddresses) ? new HashSet<>() : customerAddresses;
    }

    public boolean isCompany() {
        return isCompany != null && isCompany;
    }


}
