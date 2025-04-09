package pl.yummy.api.dto.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.CustomerActivityHistoryViewDTO;
import pl.yummy.domain.CustomerActivityHistoryView;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface CustomerActivityHistoryViewMapper extends OffsetDateTimeMapper{


    @Mapping(source = "customerId", target = "customerId", qualifiedByName = "integerToLong")
    // Główne mapowanie recentOrders.orderDate wykorzystuje kwalifikator, więc w wynikowym DTO pole orderDate będzie typu String.
    @Mapping(source = "recentOrders.orderDate", target = "recentOrders.orderDate", qualifiedByName = "mapOffsetDateTimeToString")
    CustomerActivityHistoryViewDTO toDTO(CustomerActivityHistoryView history);

    @Mapping(source = "customerId", target = "customerId", qualifiedByName = "longToInteger")
    CustomerActivityHistoryView toDomain(CustomerActivityHistoryViewDTO dto);

    @IterableMapping(qualifiedByName = "mapRecentOrderToDTO")
    List<CustomerActivityHistoryViewDTO.RecentOrder> toDTORecentOrders(List<CustomerActivityHistoryView.RecentOrder> recentOrders);

    @IterableMapping(qualifiedByName = "mapRecentOrderToDomain")
    List<CustomerActivityHistoryView.RecentOrder> toDomainRecentOrders(List<CustomerActivityHistoryViewDTO.RecentOrder> recentOrders);


    @Named("mapRecentOrderToDTO")
    default CustomerActivityHistoryViewDTO.RecentOrder mapRecentOrderToDTO(CustomerActivityHistoryView.RecentOrder order) {
        if (order == null) {
            return null;
        }
        return CustomerActivityHistoryViewDTO.RecentOrder.builder()
                .orderId(order.getOrderId())
                // Konwersja z OffsetDateTime (domena) na String (DTO)
                .orderDate(mapOffsetDateTimeToString(order.getOrderDate()))
                .status(order.getStatus())
                .amount(order.getAmount())
                .build();
    }

    @Named("mapRecentOrderToDomain")
    default CustomerActivityHistoryView.RecentOrder mapRecentOrderToDomain(CustomerActivityHistoryViewDTO.RecentOrder orderDTO) {
        if (orderDTO == null) {
            return null;
        }
        return CustomerActivityHistoryView.RecentOrder.builder()
                .orderId(orderDTO.getOrderId())
                // Konwersja z String (DTO) na OffsetDateTime (domena)
                .orderDate(mapStringToOffsetDateTime(orderDTO.getOrderDate()))
                .status(orderDTO.getStatus())
                .amount(orderDTO.getAmount())
                .build();
    }

    @Named("integerToLong")
    default Long integerToLong(Integer value) {
        return Optional.ofNullable(value).map(Integer::longValue).orElse(null);
    }

    @Named("longToInteger")
    default Integer longToInteger(Long value) {
        return Optional.ofNullable(value).map(Long::intValue).orElse(null);
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring")
    Dzięki temu mapper jest rejestrowany jako bean Springa, co ułatwia jego użycie w aplikacji.

    - toDTO/toDomain
    Główne metody mapujące między domeną a DTO. W polu customerId zastosowano kwalifikowane metody, aby przekonwertować
    typ Integer na Long i odwrotnie.

    - Mapowanie kolekcji RecentOrder
    Metody z adnotacją @IterableMapping wraz z kwalifikatorem umożliwiają odwzorowanie listy zagnieżdżonych
    obiektów RecentOrder. Definiujemy dwie metody pomocnicze (mapRecentOrderToDTO oraz mapRecentOrderToDomain),
    które tworzą obiekty builderami.

    - Metody pomocnicze do konwersji typów
    Metody mapIntegerToLong i mapLongToInteger zabezpieczają konwersję między Integer a Long, co zapewnia spójność
    między modelem domenowym a DTO.


    Podsumowując, dodatkowe rozwiązania (mapowanie kolekcji, kwalifikowane metody konwersji) są tutaj przydatne,
    ponieważ umożliwiają bezpieczną i spójną konwersję pomiędzy typami, które nie są identyczne w obu warstwach.
    */
