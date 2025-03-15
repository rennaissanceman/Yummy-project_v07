package pl.yummy.api.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.OrderItemDTO;
import pl.yummy.api.dto.OrdersDTO;
import pl.yummy.domain.AvailableDeliveryArea;
import pl.yummy.domain.CustomerAddress;
import pl.yummy.domain.Orders;
import pl.yummy.domain.OrdersItem;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {OffsetDateTimeMapper.class,
                CustomerMapper.class,
                PaymentMapper.class,
                InvoiceMapper.class,
                DeliveryMapper.class,
                OrderItemMapper.class})
public interface OrdersMapper extends OffsetDateTimeMapper{

    @Mapping(source = "menu.menuId", target = "menuId")
    @Mapping(source = "ordersStatus", target = "ordersStatus", qualifiedByName = "mapOrdersStatus")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "availableDeliveryAreaId", target = "availableDeliveryAreaId", qualifiedByName = "mapAvailableDeliveryAreaId")
    @Mapping(source = "customerAddressId", target = "customerAddressId", qualifiedByName = "mapCustomerAddressId")
    @Mapping(source = "ordersItems", target = "ordersItems", qualifiedByName = "mapOrdersItems")
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
                // Przykładowe sortowanie po identyfikatorze – zakładając, że OrdersItem posiada metodę getOrdersItemId()
                .sorted((o1, o2) -> Long.compare(o1.getOrdersItemId(), o2.getOrdersItemId()))
                .map(this::toOrderItemDTO)
                .collect(Collectors.toList());
    }

    // Delegowana metoda mapująca pojedynczy OrdersItem, której implementacja zostanie wygenerowana przez OrderItemMapper
    OrderItemDTO toOrderItemDTO(OrdersItem ordersItem);

    // Metoda toDomain – mapowanie z OrdersDTO na Orders
    @InheritInverseConfiguration(name = "toDTO")
    @Mapping(source = "ordersStatus", target = "ordersStatus", qualifiedByName = "mapOrdersStatusToEnum")
    Orders toDomain(OrdersDTO ordersDTO);

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