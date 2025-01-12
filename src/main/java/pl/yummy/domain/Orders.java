package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.domain.enums.PaymentStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "ordersId")
@ToString(of = {"ordersId", "ordersNumber", "customer", "menu", "ordersDateTime", "ordersStatus", "ordersDescription",
        "totalAmount", "availableDeliveryAreaId", "customerAddressId"})
public class Orders {

    Integer ordersId;
    String ordersNumber;
    Customer customer;
    Menu menu;
    OffsetDateTime ordersDateTime;
    OrdersStatusEnumDomain ordersStatus;
    String ordersDescription;
    BigDecimal totalAmount;
    AvailableDeliveryArea availableDeliveryAreaId;
    CustomerAddress customerAddressId;
    Payment payment;
    Invoice invoice;
    Receipt receipt;
    Delivery delivery;
    Set<OrdersItem> ordersItems;

    public Set<OrdersItem> getOrdersItems() {
        return Objects.isNull(ordersItems) ? new HashSet<>() : ordersItems;
    }

    public boolean shouldBeProcessed() {
        return customer != null
                && ordersItems != null && !ordersItems.isEmpty()
                && totalAmount != null && totalAmount.compareTo(BigDecimal.ZERO) > 0
                && payment != null && payment.getPaymentStatus() == PaymentStatusEnumDomain.IN_PROGRESS
                && ordersStatus == OrdersStatusEnumDomain.PENDING;
    }
}
