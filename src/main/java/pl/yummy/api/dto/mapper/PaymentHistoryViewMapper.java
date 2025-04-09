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
public interface PaymentHistoryViewMapper extends OffsetDateTimeMapper{

    @Mapping(source = "orderProcessingEvents", target = "orderProcessingEvents", qualifiedByName = "mapEventsToDTO")
    OrderHistoryViewDTO toDTO(OrderHistoryView orderHistoryView);

    @Mapping(source = "orderProcessingEvents", target = "orderProcessingEvents", qualifiedByName = "mapEventsToDomain")
    OrderHistoryView toDomain(OrderHistoryViewDTO dto);

    @Named("mapEventsToDTO")
    default List<OrderHistoryViewDTO.OrderProcessingEvent> mapEventsToDTO(List<OrderHistoryView.OrderProcessingEvent> events) {
        if (events == null) {
            return Collections.emptyList();
        }
        return events.stream()
                .map(this::toDTOEvent)
                .collect(Collectors.toList());
    }

    default OrderHistoryViewDTO.OrderProcessingEvent toDTOEvent(OrderHistoryView.OrderProcessingEvent event) {
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
                .map(this::toDomainEvent)
                .collect(Collectors.toList());
    }

    default OrderHistoryView.OrderProcessingEvent toDomainEvent(OrderHistoryViewDTO.OrderProcessingEvent dto) {
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
    - Główne metody mapujące:
    Metody toDTO oraz toDomain odwzorowują obiekt ViewOrderHistory na ViewOrderHistoryDTO (i odwrotnie).

    - Mapowanie listy orderProcessingEvents:
    Metody mapEventsToDTO oraz mapEventsToDomain zabezpieczają mapowanie – jeśli przekazana lista jest null,
    zwracana jest pusta lista (Collections.emptyList()), co zapobiega wystąpieniu NullPointerException.

    - Mapowanie pojedynczych elementów:
    Metody toDTOEvent oraz toDomainEvent mapują pojedynczy element (OrderProcessingEvent) przy użyciu builderów.
    Dzięki temu mamy pełną kontrolę nad odwzorowaniem wszystkich właściwości tego zagnieżdżonego typu.


    Takie podejście zwiększa odporność mappera na błędy oraz zapewnia spójność danych między warstwą domenową a DTO.
    */