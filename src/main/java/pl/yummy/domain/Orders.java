package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
}
