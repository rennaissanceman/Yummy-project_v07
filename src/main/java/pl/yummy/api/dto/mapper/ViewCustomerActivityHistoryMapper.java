package pl.yummy.api.dto.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.ViewCustomerActivityHistoryDTO;
import pl.yummy.domain.ViewCustomerActivityHistory;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ViewCustomerActivityHistoryMapper {

    @Mapping(source = "customerId", target = "customerId", qualifiedByName = "integerToLong")
    ViewCustomerActivityHistoryDTO toDTO(ViewCustomerActivityHistory history);

    @Mapping(source = "customerId", target = "customerId", qualifiedByName = "longToInteger")
    ViewCustomerActivityHistory toDomain(ViewCustomerActivityHistoryDTO dto);

    @IterableMapping(qualifiedByName = "mapRecentOrderToDTO")
    List<ViewCustomerActivityHistoryDTO.RecentOrder> toDTORecentOrders(List<ViewCustomerActivityHistory.RecentOrder> recentOrders);

    @IterableMapping(qualifiedByName = "mapRecentOrderToDomain")
    List<ViewCustomerActivityHistory.RecentOrder> toDomainRecentOrders(List<ViewCustomerActivityHistoryDTO.RecentOrder> recentOrders);

    @Named("mapRecentOrderToDTO")
    default ViewCustomerActivityHistoryDTO.RecentOrder mapRecentOrderToDTO(ViewCustomerActivityHistory.RecentOrder order) {
        if (order == null) {
            return null;
        }
        return ViewCustomerActivityHistoryDTO.RecentOrder.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .amount(order.getAmount())
                .build();
    }

    @Named("mapRecentOrderToDomain")
    default ViewCustomerActivityHistory.RecentOrder mapRecentOrderToDomain(ViewCustomerActivityHistoryDTO.RecentOrder orderDTO) {
        if (orderDTO == null) {
            return null;
        }
        return ViewCustomerActivityHistory.RecentOrder.builder()
                .orderId(orderDTO.getOrderId())
                .orderDate(orderDTO.getOrderDate())
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
