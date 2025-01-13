package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.CustomerActivityHistory;
import pl.yummy.infrastructure.database.entity.*;
import pl.yummy.infrastructure.database.repository.mapper.enums.OrdersStatusEnumMapper;
import pl.yummy.infrastructure.database.repository.mapper.enums.PaymentStatusEnumMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        AddressEntityMapper.class,
        BillingInformationEntityMapper.class,
        OrdersStatusEnumMapper.class,
        PaymentStatusEnumMapper.class
})
public interface CustomerActivityHistoryMapper {

    @Mapping(source = "customer.customerAddresses", target = "customerAddresses")
    @Mapping(source = "customer.orders", target = "orders", qualifiedByName = "mapOrders")
    @Mapping(source = "customer.invoices", target = "invoices", qualifiedByName = "mapInvoices")
    @Mapping(source = "customer.billingInformation", target = "billingInformation")
    CustomerActivityHistory mapFromEntity(CustomerEntity customer);


    @Named("mapOrders")
    default List<CustomerActivityHistory.OrderHistory> mapOrders(Set<OrdersEntity> orders) {
        return orders.stream()
                .map(order -> CustomerActivityHistory.OrderHistory.builder()
                        .orderId(order.getOrdersId())
                        .orderNumber(order.getOrdersNumber())
                        .orderDate(order.getOrdersDateTime())
                        .status(OrdersStatusEnumMapper.toDomain(order.getOrdersStatus())) // Użycie mappera
                        .totalAmount(order.getTotalAmount())
                        .items(mapOrderItems(order.getOrdersItems()))
                        .build())
                .collect(Collectors.toList());
    }

    default List<CustomerActivityHistory.OrderHistory.OrderItemDetails> mapOrderItems(Set<OrdersItemEntity> items) {
        return items.stream()
                .map(item -> CustomerActivityHistory.OrderHistory.OrderItemDetails.builder()
                        .itemId(item.getOrdersItemId())
                        .itemName(item.getItemName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .totalPrice(item.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }


    @Named("mapInvoices")
    default List<CustomerActivityHistory.InvoiceDetails> mapInvoices(Set<InvoiceEntity> invoices) {
        return invoices.stream()
                .map(invoice -> CustomerActivityHistory.InvoiceDetails.builder()
                        .invoiceId(invoice.getInvoiceId())
                        .invoiceNumber(invoice.getInvoiceNumber())
                        .totalAmount(invoice.getTotalAmount())
                        .paymentStatus(PaymentStatusEnumMapper.toDomain(invoice.getPayment().getPaymentStatus())) // Użycie mappera
                        .issueDate(invoice.getIssueDate())
                        .build())
                .collect(Collectors.toList());
    }
}
