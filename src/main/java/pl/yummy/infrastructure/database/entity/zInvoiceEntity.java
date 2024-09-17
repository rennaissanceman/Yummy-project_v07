package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class zInvoiceEntity {

    private Integer invoiceId;
    private zOrderEntity order;
    private OffsetDateTime issueDate;
    private OffsetDateTime saleDate;
    private BigDecimal totalAmount;
    private BigDecimal netAmount;
    private BigDecimal taxRate;
    private String paymentMethod;
    private String paymentStatus;
    private zCustomerEntity customer;
    private zBillingInformationEntity billingInformation;
    private String notes;
    private OffsetDateTime dueDate;
    private String issuerSignature;
}
