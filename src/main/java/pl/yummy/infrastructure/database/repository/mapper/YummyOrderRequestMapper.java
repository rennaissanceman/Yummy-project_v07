package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.YummyOrderRequest;
import pl.yummy.infrastructure.database.entity.OrdersEntity;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface YummyOrderRequestMapper {

    @Mapping(source = "yummyOrderRequest.customerName", target = "customer.customerName")
    @Mapping(source = "yummyOrderRequest.customerSurname", target = "customer.customerSurname")
    @Mapping(source = "yummyOrderRequest.customerPhone", target = "customer.userAuth.phone")
    @Mapping(source = "yummyOrderRequest.customerEmail", target = "customer.userAuth.email")
    @Mapping(source = "yummyOrderRequest.deliveryCountry", target = "availableDeliveryArea.address.country")
    @Mapping(source = "yummyOrderRequest.deliveryCity", target = "availableDeliveryArea.address.city")
    @Mapping(source = "yummyOrderRequest.deliveryPostalCode", target = "availableDeliveryArea.address.postalCode")
    @Mapping(source = "yummyOrderRequest.deliveryStreet", target = "availableDeliveryArea.address.street")
    @Mapping(source = "yummyOrderRequest.menuItemName", target = "ordersItems.itemName")
    @Mapping(source = "yummyOrderRequest.quantity", target = "ordersItems.quantity")
    @Mapping(source = "yummyOrderRequest.specialInstructions", target = "orders.ordersDescription")
    @Mapping(source = "yummyOrderRequest.restaurantName", target = "restaurant.restaurantName")
    @Mapping(source = "yummyOrderRequest.restaurantPhone", target = "restaurant.phone")
    @Mapping(source = "yummyOrderRequest.restaurantEmail", target = "restaurant.email")
    @Mapping(source = "yummyOrderRequest.courierName", target = "courier.courierName")
    @Mapping(source = "yummyOrderRequest.courierSurname", target = "courier.courierSurname")
    @Mapping(source = "yummyOrderRequest.courierVehicleType", target = "courier.vehicleType")
    @Mapping(source = "yummyOrderRequest.paymentMethodName", target = "payment.paymentMethod.paymentMethodName")
    @Mapping(source = "yummyOrderRequest.paymentStatus", target = "payment.paymentStatus")
    @Mapping(source = "yummyOrderRequest.totalAmount", target = "orders.totalAmount")
    OrdersEntity mapToEntity(YummyOrderRequest yummyOrderRequest);

    @Mapping(source = "orders.customer.customerName", target = "customerName")
    @Mapping(source = "orders.customer.customerSurname", target = "customerSurname")
    @Mapping(source = "orders.customer.userAuth.phone", target = "customerPhone")
    @Mapping(source = "orders.customer.userAuth.email", target = "customerEmail")
    @Mapping(source = "orders.availableDeliveryArea.address.country", target = "deliveryCountry")
    @Mapping(source = "orders.availableDeliveryArea.address.city", target = "deliveryCity")
    @Mapping(source = "orders.availableDeliveryArea.address.postalCode", target = "deliveryPostalCode")
    @Mapping(source = "orders.availableDeliveryArea.address.street", target = "deliveryStreet")
    @Mapping(source = "orders.ordersItems", target = "menuItemName", qualifiedByName = "extractMenuItemName")
    @Mapping(source = "orders.ordersItems", target = "quantity", qualifiedByName = "extractQuantity")
    @Mapping(source = "orders.ordersDescription", target = "specialInstructions")
    @Mapping(source = "orders.restaurant.restaurantName", target = "restaurantName")
    @Mapping(source = "orders.restaurant.phone", target = "restaurantPhone")
    @Mapping(source = "orders.restaurant.email", target = "restaurantEmail")
    @Mapping(source = "orders.courier.courierName", target = "courierName")
    @Mapping(source = "orders.courier.courierSurname", target = "courierSurname")
    @Mapping(source = "orders.courier.vehicleType", target = "courierVehicleType")
    @Mapping(source = "orders.payment.paymentMethod.paymentMethodName", target = "paymentMethodName")
    @Mapping(source = "orders.payment.paymentStatus", target = "paymentStatus")
    @Mapping(source = "orders.totalAmount", target = "totalAmount")
    YummyOrderRequest mapFromEntity(OrdersEntity orders);

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
}
