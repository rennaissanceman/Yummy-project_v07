package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.YummyCustomerActivityHistory;
import pl.yummy.infrastructure.database.entity.CustomerEntity;
import pl.yummy.infrastructure.database.entity.InvoiceEntity;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;
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
public interface YummyCustomerActivityHistoryMapper {

    @Mapping(source = "customer.customerAddresses", target = "customerAddresses")
    @Mapping(source = "customer.orders", target = "orders", qualifiedByName = "mapOrders")
    @Mapping(source = "customer.invoices", target = "invoices", qualifiedByName = "mapInvoices")
    @Mapping(source = "customer.billingInformation", target = "billingInformation")
    YummyCustomerActivityHistory mapFromEntity(CustomerEntity customer);


    @Named("mapOrders")
    default List<YummyCustomerActivityHistory.OrderHistory> mapOrders(Set<OrdersEntity> orders) {
        return orders.stream()
                .map(order -> YummyCustomerActivityHistory.OrderHistory.builder()
                        .orderId(order.getOrdersId())
                        .orderNumber(order.getOrdersNumber())
                        .orderDate(order.getOrdersDateTime())
                        .status(OrdersStatusEnumMapper.toDomain(order.getOrdersStatus())) // Użycie mappera
                        .totalAmount(order.getTotalAmount())
                        .items(mapOrderItems(order.getOrdersItems()))
                        .build())
                .collect(Collectors.toList());
    }

    default List<YummyCustomerActivityHistory.OrderHistory.OrderItemDetails> mapOrderItems(Set<OrdersItemEntity> items) {
        return items.stream()
                .map(item -> YummyCustomerActivityHistory.OrderHistory.OrderItemDetails.builder()
                        .itemId(item.getOrdersItemId())
                        .itemName(item.getItemName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .totalPrice(item.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }


    @Named("mapInvoices")
    default List<YummyCustomerActivityHistory.InvoiceDetails> mapInvoices(Set<InvoiceEntity> invoices) {
        return invoices.stream()
                .map(invoice -> YummyCustomerActivityHistory.InvoiceDetails.builder()
                        .invoiceId(invoice.getInvoiceId())
                        .invoiceNumber(invoice.getInvoiceNumber())
                        .totalAmount(invoice.getTotalAmount())
                        .paymentStatus(PaymentStatusEnumMapper.toDomain(invoice.getPayment().getPaymentStatus())) // Użycie mappera
                        .issueDate(invoice.getIssueDate())
                        .build())
                .collect(Collectors.toList());
    }
}
