package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.ViewOrderHistoryDTO;
import pl.yummy.api.dto.ViewPaymentHistoryDTO;
import pl.yummy.domain.ViewOrderHistory;
import pl.yummy.domain.ViewPaymentHistory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ViewPaymentHistoryMapper {

    @Mapping(source = "orderProcessingEvents", target = "orderProcessingEvents", qualifiedByName = "mapEventsToDTO")
    ViewOrderHistoryDTO toDTO(ViewOrderHistory viewOrderHistory);

    @Mapping(source = "orderProcessingEvents", target = "orderProcessingEvents", qualifiedByName = "mapEventsToDomain")
    ViewOrderHistory toDomain(ViewOrderHistoryDTO dto);

    @Named("mapEventsToDTO")
    default List<ViewOrderHistoryDTO.OrderProcessingEvent> mapEventsToDTO(List<ViewOrderHistory.OrderProcessingEvent> events) {
        if (events == null) {
            return Collections.emptyList();
        }
        return events.stream()
                .map(this::toDTOEvent)
                .collect(Collectors.toList());
    }

    default ViewOrderHistoryDTO.OrderProcessingEvent toDTOEvent(ViewOrderHistory.OrderProcessingEvent event) {
        if (event == null) {
            return null;
        }
        return ViewOrderHistoryDTO.OrderProcessingEvent.builder()
                .eventNumber(event.getEventNumber())
                .receivedDateTime(event.getReceivedDateTime())
                .completedDateTime(event.getCompletedDateTime())
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
    default List<ViewOrderHistory.OrderProcessingEvent> mapEventsToDomain(List<ViewOrderHistoryDTO.OrderProcessingEvent> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::toDomainEvent)
                .collect(Collectors.toList());
    }

    default ViewOrderHistory.OrderProcessingEvent toDomainEvent(ViewOrderHistoryDTO.OrderProcessingEvent dto) {
        if (dto == null) {
            return null;
        }
        return ViewOrderHistory.OrderProcessingEvent.builder()
                .eventNumber(dto.getEventNumber())
                .receivedDateTime(dto.getReceivedDateTime())
                .completedDateTime(dto.getCompletedDateTime())
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