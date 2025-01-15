package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.YummyOrderProcessingRequest;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatus;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YummyOrderProcessingRequestMapper {

    @Mapping(source = "order.ordersId", target = "orderId")
    @Mapping(source = "order.customer.userAuth.email", target = "customerEmail")
    @Mapping(source = "order.menu.restaurant.restaurantName", target = "restaurantName")
    @Mapping(source = "order.ordersItems", target = "menuItemName", qualifiedByName = "extractMenuItemName")
    @Mapping(source = "order.ordersItems", target = "quantity", qualifiedByName = "extractQuantity")
    @Mapping(source = "order.delivery.courier.courierName", target = "courierName")
    @Mapping(source = "order.delivery.courier.courierStatus", target = "courierStatus")
    @Mapping(source = "order.delivery.estimatedDeliveryTime", target = "expectedDeliveryTime")
    @Mapping(source = "order.payment.paymentStatus", target = "paymentStatus")
    @Mapping(source = "order.ordersStatus", target = "isCompleted", qualifiedByName = "mapOrderCompletion")
    YummyOrderProcessingRequest mapFromEntity(OrdersEntity order);

    @Named("extractMenuItemName")
    default String extractMenuItemName(Set<OrdersItemEntity> items) {
        return items.stream()
                .findFirst()
                .map(OrdersItemEntity::getItemName)
                .orElse(null);
    }

    @Named("extractQuantity")
    default Integer extractQuantity(Set<OrdersItemEntity> items) {
        return items.stream()
                .findFirst()
                .map(OrdersItemEntity::getQuantity)
                .orElse(null);
    }

    @Named("mapOrderCompletion")
    default Boolean mapOrderCompletion(OrdersStatus status) {
        return status != null && status.equals(OrdersStatus.CONFIRMED);
    }
}
