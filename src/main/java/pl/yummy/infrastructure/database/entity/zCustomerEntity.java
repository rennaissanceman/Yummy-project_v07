package pl.yummy.infrastructure.database.entity;

public class zCustomerEntity {

    private Integer customerId;
    private String customerType;
    private String email;
    private String phoneNumber;
    private zAddressEntity address;
    private String name;
    private String surname;
    private String companyName;
    private String createdAt;
    private String updatedAt;


    private zOrderEntity order;
    private zDeliveryEntity delivery;
    private zInvoiceEntity invoice;
    private zBillingInformationEntity billingInformation;

}
