package pl.yummy.infrastructure.database.entity;

public class CustomerEntity {

    private Integer customerId;
    private String customerType;
    private String email;
    private String phoneNumber;
    private AddressEntity address;
    private String name;
    private String surname;
    private String companyName;
    private String createdAt;
    private String updatedAt;


    private OrderEntity order;
    private DeliveryEntity delivery;
    private InvoiceEntity invoice;
    private BillingInformationEntity billingInformation;

}
