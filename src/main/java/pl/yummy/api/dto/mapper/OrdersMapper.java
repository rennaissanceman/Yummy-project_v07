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

@SuppressWarnings("all")
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CustomerMapper.class, PaymentMapper.class, InvoiceMapper.class, DeliveryMapper.class, OrderItemMapper.class})
public interface OrdersMapper extends OffsetDateTimeMapper{

    // Nadpisujemy metody konwersji z OffsetDateTimeMapper z unikalnymi kwalifikatorami,
    // aby MapStruct jednoznacznie wybierał nasze metody dla pola ordersDateTime.
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

    @Mapping(source = "menu.menuId", target = "menuId")
    @Mapping(source = "ordersStatus", target = "ordersStatus", qualifiedByName = "mapOrdersStatus")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "ordersMapOffsetDateTimeToString")
    @Mapping(source = "availableDeliveryAreaId", target = "availableDeliveryAreaId", qualifiedByName = "mapAvailableDeliveryAreaId")
    @Mapping(source = "customerAddressId", target = "customerAddressId", qualifiedByName = "mapCustomerAddressId")
    @Mapping(source = "ordersItems", target = "ordersItems", qualifiedByName = "mapOrdersItems")
// Usuń mapping na "receipt" - jeśli OrdersDTO nie posiada tego pola, po prostu nie mapuj.
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
    // @InheritInverseConfiguration odziedziczy konfigurację z toDTO, ale uzupełniamy mapowanie dla ordersDateTime.
    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "ordersMapStringToOffsetDateTime")
    @Mapping(source = "ordersStatus", target = "ordersStatus", qualifiedByName = "mapOrdersStatusToEnum")
    @Mapping(source = "availableDeliveryAreaId", target = "availableDeliveryAreaId", qualifiedByName = "mapAvailableDeliveryAreaIdInverse")
    @Mapping(source = "customerAddressId", target = "customerAddressId", qualifiedByName = "mapCustomerAddressIdInverse")
    @Mapping(target = "ordersItems", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "receipt", ignore = true)
    @Mapping(target = "customer", ignore = true) // Ignorujemy zagnieżdżoną konwersję obiektu customer
    Orders toDomain(OrdersDTO ordersDTO);

    @Named("mapAvailableDeliveryAreaIdInverse")
    default AvailableDeliveryArea mapAvailableDeliveryAreaIdInverse(Long id) {
        // W rzeczywistości należałoby pobrać obiekt AvailableDeliveryArea według identyfikatora; tutaj zwracamy null.
        return null;
    }

    @Named("mapCustomerAddressIdInverse")
    default CustomerAddress mapCustomerAddressIdInverse(Long id) {
        // Analogicznie dla CustomerAddress.
        return null;
    }

    @Named("mapOrdersStatusToEnum")
    default OrdersStatusEnumDomain mapOrdersStatusToEnum(String status) {
        return status == null ? null : OrdersStatusEnumDomain.valueOf(status);
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring", uses = {…})
    Mapper jest rejestrowany jako bean Springa, a dodatkowe mappery (CustomerMapper, PaymentMapper, InvoiceMapper,
    DeliveryMapper, OrderItemMapper) są używane do mapowania zagnieżdżonych obiektów.

    - Mapowanie pola menuId:
    Używamy @Mapping(source = "menu.menuId", target = "menuId") do wyodrębnienia identyfikatora menu.

    - Kwalifikowane mapowanie ordersStatus:
    Metoda domyślna mapOrdersStatus konwertuje wartość enuma na String, zabezpieczając przed null.

    - Bezpieczne mapowanie kolekcji ordersItems:
    Metoda mapOrdersItems przekształca zbiór na listę, zwracając pustą listę, gdy przekazany zbiór jest null.
    Dodatkowo elementy są sortowane (przykładowo po identyfikatorze).

    - Delegacja mapowania pojedynczych elementów:
    Metoda toOrderItemDTO deleguje mapowanie pojedynczego obiektu OrdersItem do dedykowanego mappera (OrderItemMapper).


    Takie rozwiązanie zwiększa odporność mappera na błędy (np. null pointer exceptions) i zapewnia spójność mapowania
    z podejściem stosowanym w innych mapperach.
    */