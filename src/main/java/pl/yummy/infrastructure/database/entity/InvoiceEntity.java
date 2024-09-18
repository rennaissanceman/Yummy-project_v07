package pl.yummy.infrastructure.database.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class InvoiceEntity {

    private Integer invoiceId;
    private String invoiceNumber;
    private OrderEntity order;
    private OffsetDateTime issueDate;
    private OffsetDateTime saleDate;
    private BigDecimal totalAmount;
    private BigDecimal netAmount;
    private BigDecimal taxRate;
    private PaymentEntity payment;
    private CustomerEntity customer;
    private String notes;
    private OffsetDateTime dueDate;
    private String issuerSignature;


}
