package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.YummyOrderHistory;
import pl.yummy.infrastructure.database.entity.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YummyOrderHistoryMapper {

    @Mapping(source = "customer.customerId", target = "customerId")
    @Mapping(source = "customer.orders", target = "orderDetails", qualifiedByName = "mapOrderDetails")
    YummyOrderHistory mapFromEntity(CustomerEntity customer);

    @Named("mapOrderDetails")
    default List<YummyOrderHistory.OrderDetail> mapOrderDetails(Set<OrdersEntity> orders) {
        return orders.stream()
                .map(order -> YummyOrderHistory.OrderDetail.builder()
                        .orderId(order.getOrdersId().toString())
                        .orderDate(order.getOrdersDateTime())
                        .deliveryDate(order.getDelivery().getActualDeliveryDateTime())
                        .restaurantName(order.getMenu().getRestaurant().getRestaurantName())
                        .menuItems(mapMenuItems(order.getOrdersItems()))
                        .totalAmount(order.getTotalAmount())
                        .status(order.getOrdersStatus().name())
                        .customerNote(order.getOrdersDescription())
                        .paymentMethod(order.getPayment().getPaymentMethod().getPaymentMethodName())
                        .deliveryInfo(mapDeliveryInfo(order.getDelivery()))
                        .courier(mapCourier(order.getDelivery().getCourier()))
                        .build())
                .collect(Collectors.toList());
    }

    default List<YummyOrderHistory.OrderDetail.MenuItem> mapMenuItems(Set<OrdersItemEntity> items) {
        return items.stream()
                .map(item -> YummyOrderHistory.OrderDetail.MenuItem.builder()
                        .itemName(item.getItemName())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .totalPrice(item.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }

    default YummyOrderHistory.OrderDetail.DeliveryInfo mapDeliveryInfo(DeliveryEntity delivery) {
        return YummyOrderHistory.OrderDetail.DeliveryInfo.builder()
                .deliveryAddress(delivery.getAvailableDeliveryArea().getAddress().toString())
                .estimatedDeliveryTime(delivery.getEstimatedDeliveryTime())
                .deliveryInstructions(delivery.getDeliveryNotes())
                .build();
    }

    default YummyOrderHistory.OrderDetail.Courier mapCourier(CourierEntity courier) {
        if (courier == null) {
            return null;
        }
        return YummyOrderHistory.OrderDetail.Courier.builder()
                .courierId(courier.getCourierId().toString())
                .courierName(courier.getCourierName() + " " + courier.getCourierSurname())
                .courierPhone(courier.getUserAuth().getPhone())
                .build();
    }
}
