package pl.yummy.api.dto.mapper;

import org.mapstruct.*;
import pl.yummy.api.dto.OrderItemDTO;
import pl.yummy.api.dto.OrdersDTO;
import pl.yummy.domain.AvailableDeliveryArea;
import pl.yummy.domain.CustomerAddress;
import pl.yummy.domain.Orders;
import pl.yummy.domain.OrdersItem;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CustomerMapper.class,
                PaymentMapper.class,
                InvoiceMapper.class,
                DeliveryMapper.class,
                OrderItemMapper.class})
public interface MenuMapper extends OffsetDateTimeMapper{

    // Nadpisujemy metodę konwersji z OffsetDateTimeMapper z unikalnymi kwalifikatorami,
    // aby jednoznacznie określić jej implementację.
    @Override
    @Named("ordersMapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return OffsetDateTimeMapper.super.mapOffsetDateTimeToString(offsetDateTime);
    }

    @Override
    @Named("ordersMapStringToOffsetDateTime")
    default OffsetDateTime mapStringToOffsetDateTime(String dateTimeString) {
        return OffsetDateTimeMapper.super.mapStringToOffsetDateTime(dateTimeString);
    }

    // Mapowanie z domeny (Orders) do DTO (OrdersDTO)
    @Mapping(source = "menu.menuId", target = "menuId")
    @Mapping(source = "ordersStatus", target = "ordersStatus", qualifiedByName = "mapOrdersStatus")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "ordersMapOffsetDateTimeToString")
    @Mapping(source = "availableDeliveryAreaId", target = "availableDeliveryAreaId", qualifiedByName = "mapAvailableDeliveryAreaId")
    @Mapping(source = "customerAddressId", target = "customerAddressId", qualifiedByName = "mapCustomerAddressId")
    @Mapping(source = "ordersItems", target = "ordersItems", qualifiedByName = "mapOrdersItems")
// Ignorujemy tylko pola, które faktycznie istnieją w DTO (invoice jest ignorowany, receipt nie występuje)
    @Mapping(target = "invoice", ignore = true)
    OrdersDTO toDTO(Orders orders);

    @Named("mapOrdersStatus")
    default String mapOrdersStatus(OrdersStatusEnumDomain status) {
        return status == null ? null : status.name();
    }

    @Named("mapAvailableDeliveryAreaId")
    default Long mapAvailableDeliveryAreaId(AvailableDeliveryArea availableDeliveryArea) {
        return availableDeliveryArea == null ? null : availableDeliveryArea.getAvailableDeliveryAreaId();
    }

    @Named("mapCustomerAddressId")
    default Long mapCustomerAddressId(CustomerAddress customerAddress) {
        return customerAddress == null ? null : customerAddress.getCustomerAddressId();
    }

    @Named("mapOrdersItems")
    default List<OrderItemDTO> mapOrdersItems(Set<OrdersItem> ordersItems) {
        if (ordersItems == null) {
            return Collections.emptyList();
        }
        return ordersItems.stream()
                .sorted((o1, o2) -> Long.compare(o1.getOrdersItemId(), o2.getOrdersItemId()))
                .map(this::toOrderItemDTO)
                .collect(Collectors.toList());
    }

    // Delegujemy mapowanie pojedynczego OrdersItem – implementacja zostanie wygenerowana przez OrderItemMapper.
    @Mapping(source = "ordersItemId", target = "menuItemId")
    OrderItemDTO toOrderItemDTO(OrdersItem ordersItem);

    // Mapowanie odwrotne z DTO (OrdersDTO) na domenę (Orders)
    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "ordersMapStringToOffsetDateTime")
    @Mapping(source = "ordersStatus", target = "ordersStatus", qualifiedByName = "mapOrdersStatusToEnum")
    @Mapping(source = "availableDeliveryAreaId", target = "availableDeliveryAreaId", qualifiedByName = "mapAvailableDeliveryAreaIdInverse")
    @Mapping(source = "customerAddressId", target = "customerAddressId", qualifiedByName = "mapCustomerAddressIdInverse")
    // Ignorujemy odwrotne mapowanie ordersItems, invoice oraz receipt.
    @Mapping(target = "ordersItems", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "receipt", ignore = true)
    Orders toDomain(OrdersDTO ordersDTO);

    @Named("mapAvailableDeliveryAreaIdInverse")
    default AvailableDeliveryArea mapAvailableDeliveryAreaIdInverse(Long id) {
        // W rzeczywistości należałoby pobrać obiekt AvailableDeliveryArea według identyfikatora; tutaj zwracamy null.
        return null;
    }

    @Named("mapCustomerAddressIdInverse")
    default CustomerAddress mapCustomerAddressIdInverse(Long id) {
        // Podobnie dla CustomerAddress.
        return null;
    }

    @Named("mapOrdersStatusToEnum")
    default OrdersStatusEnumDomain mapOrdersStatusToEnum(String status) {
        return status == null ? null : OrdersStatusEnumDomain.valueOf(status);
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring", uses = {MenuItemMapper.class})
    Mapper jest rejestrowany jako bean Springa, a dzięki atrybutowi uses do mapowania zagnieżdżonych elementów menuItems
    wykorzystywany jest MenuItemMapper.

    - Mapowanie restaurantId:
    Pole restaurant.restaurantId z domeny jest odwzorowywane na pole restaurantId w DTO.

    - Mapowanie kolekcji menuItems:
    Używamy metody domyślnej mapMenuItems, która:
    - Zwraca pustą listę, gdy przekazany zbiór jest null.
    - Sortuje elementy według menuItemId (przy założeniu, że metoda getMenuItemId() jest dostępna w klasie MenuItem).
    - Mapuje każdy element przy użyciu metody toDTO(MenuItem menuItem). Ta metoda jest delegowana do MenuItemMapper
    (zgodnie z konfiguracją uses).



    Taka implementacja zwiększa bezpieczeństwo (zapewnia nie-nullową listę) oraz spójność mapowania kolekcji zgodnie
    z podejściem stosowanym w innych mapperach.
    */