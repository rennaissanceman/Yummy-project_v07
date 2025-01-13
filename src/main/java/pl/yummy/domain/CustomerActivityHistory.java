package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "customerId")
@ToString(of = {"customerId", "customerNumber", "customerName", "customerSurname", "email", "orders", "invoices"})
public class CustomerActivityHistory {

    Integer customerId;
    String customerNumber;
    String customerName;
    String customerSurname;
    String email;
    Set<CustomerAddress> customerAddresses;
    List<OrderHistory> orders;
    List<InvoiceDetails> invoices;
    BillingInformation billingInformation;



    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "orderId")
    @ToString(of = {"orderId", "orderNumber", "orderDate", "status", "totalAmount"})
    public static class OrderHistory {
        Integer orderId;
        String orderNumber;
        OffsetDateTime orderDate;
        OrdersStatusEnumDomain status;
        BigDecimal totalAmount;
        List<OrderItemDetails> items;


        @With
        @Value
        @Builder
        @EqualsAndHashCode(of = "itemId")
        @ToString(of = {"itemId", "itemName", "quantity", "unitPrice", "totalPrice"})
        public static class OrderItemDetails {
            Integer itemId;
            String itemName;
            Integer quantity;
            BigDecimal unitPrice;
            BigDecimal totalPrice;
        }
    }

    @With
    @Value
    @Builder
    @EqualsAndHashCode(of = "invoiceId")
    @ToString(of = {"invoiceId", "invoiceNumber", "totalAmount", "paymentStatus", "issueDate"})
    public static class InvoiceDetails {
        Integer invoiceId;
        String invoiceNumber;
        BigDecimal totalAmount;
        PaymentStatusEnumDomain paymentStatus;
        OffsetDateTime issueDate;
    }
}
