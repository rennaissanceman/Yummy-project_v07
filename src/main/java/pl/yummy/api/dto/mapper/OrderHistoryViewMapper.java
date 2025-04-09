package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.OrderHistoryViewDTO;
import pl.yummy.domain.OrderHistoryView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface OrderHistoryViewMapper extends OffsetDateTimeMapper{

    @Mapping(source = "orderProcessingEvents", target = "orderProcessingEvents", qualifiedByName = "mapEventsToDTO")
    OrderHistoryViewDTO toDTO(OrderHistoryView history);

    @Mapping(source = "orderProcessingEvents", target = "orderProcessingEvents", qualifiedByName = "mapEventsToDomain")
    OrderHistoryView toDomain(OrderHistoryViewDTO dto);

    @Named("mapEventsToDTO")
    default List<OrderHistoryViewDTO.OrderProcessingEvent> mapEventsToDTO(List<OrderHistoryView.OrderProcessingEvent> events) {
        if (events == null) {
            return Collections.emptyList();
        }
        return events.stream()
                .map(this::mapEventToDTO)
                .collect(Collectors.toList());
    }

    @Named("mapEventToDTO")
    default OrderHistoryViewDTO.OrderProcessingEvent mapEventToDTO(OrderHistoryView.OrderProcessingEvent event) {
        if (event == null) {
            return null;
        }
        return OrderHistoryViewDTO.OrderProcessingEvent.builder()
                .eventNumber(event.getEventNumber())
                // Konwersja OffsetDateTime -> String
                .receivedDateTime(mapOffsetDateTimeToString(event.getReceivedDateTime()))
                .completedDateTime(mapOffsetDateTimeToString(event.getCompletedDateTime()))
                .customerName(event.getCustomerName())
                .restaurantName(event.getRestaurantName())
                .menuName(event.getMenuName())
                .customerComment(event.getCustomerComment())
                .totalAmount(event.getTotalAmount())
                .payments(event.getPayments())
                .deliveries(event.getDeliveries())
                .build();
    }

    @Named("mapEventsToDomain")
    default List<OrderHistoryView.OrderProcessingEvent> mapEventsToDomain(List<OrderHistoryViewDTO.OrderProcessingEvent> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::mapEventToDomain)
                .collect(Collectors.toList());
    }

    @Named("mapEventToDomain")
    default OrderHistoryView.OrderProcessingEvent mapEventToDomain(OrderHistoryViewDTO.OrderProcessingEvent dto) {
        if (dto == null) {
            return null;
        }
        return OrderHistoryView.OrderProcessingEvent.builder()
                .eventNumber(dto.getEventNumber())
                // Konwersja String -> OffsetDateTime
                .receivedDateTime(mapStringToOffsetDateTime(dto.getReceivedDateTime()))
                .completedDateTime(mapStringToOffsetDateTime(dto.getCompletedDateTime()))
                .customerName(dto.getCustomerName())
                .restaurantName(dto.getRestaurantName())
                .menuName(dto.getMenuName())
                .customerComment(dto.getCustomerComment())
                .totalAmount(dto.getTotalAmount())
                .payments(dto.getPayments())
                .deliveries(dto.getDeliveries())
                .build();
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring")
    Mapper jest rejestrowany jako bean Springa, co ułatwia jego użycie w aplikacji.

    - toDTO i toDomain
    Główne metody mapujące między obiektem domenowym ViewOrderHistory a DTO ViewOrderHistoryDTO.
    Mapowanie listy orderProcessingEvents odbywa się przy użyciu kwalifikowanych metod mapEventsToDTO
    oraz mapEventsToDomain.

    - Mapowanie listy orderProcessingEvents:
    Metody mapEventsToDTO i mapEventsToDomain zabezpieczają przed sytuacją, gdy przekazana lista jest null –
    w takim przypadku zwracana jest pusta lista.

    - Mapowanie pojedynczych elementów:
    Metody mapEventToDTO oraz mapEventToDomain mapują pojedynczy element (OrderProcessingEvent) przy użyciu builderów,
    co pozwala na pełną kontrolę nad odwzorowaniem właściwości.

    Dzięki temu rozwiązaniu mapper jest odporny na błędy związane z null (NullPointerException) i zapewnia spójne
    odwzorowanie między modelem domenowym a DTO.
    */