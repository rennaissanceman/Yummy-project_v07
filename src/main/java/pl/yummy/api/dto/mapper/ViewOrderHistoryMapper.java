package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.ViewOrderHistoryDTO;
import pl.yummy.domain.ViewOrderHistory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface ViewOrderHistoryMapper extends OffsetDateTimeMapper{

    @Mapping(source = "orderProcessingEvents", target = "orderProcessingEvents", qualifiedByName = "mapEventsToDTO")
    @Mapping(source = "orderProcessingEvents.receivedDateTime", target = "orderProcessingEvents.receivedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "orderProcessingEvents.completedDateTime", target = "orderProcessingEvents.completedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    ViewOrderHistoryDTO toDTO(ViewOrderHistory history);

    @Mapping(source = "orderProcessingEvents", target = "orderProcessingEvents", qualifiedByName = "mapEventsToDomain")
    @Mapping(source = "orderProcessingEvents.receivedDateTime", target = "orderProcessingEvents.receivedDateTime", qualifiedByName = "mapStringToOffsetDateTime")
    @Mapping(source = "orderProcessingEvents.completedDateTime", target = "orderProcessingEvents.completedDateTime", qualifiedByName = "mapStringToOffsetDateTime")
    ViewOrderHistory toDomain(ViewOrderHistoryDTO dto);

    @Named("mapEventsToDTO")
    default List<ViewOrderHistoryDTO.OrderProcessingEvent> mapEventsToDTO(List<ViewOrderHistory.OrderProcessingEvent> events) {
        if (events == null) {
            return Collections.emptyList();
        }
        return events.stream()
                .map(this::mapEventToDTO)
                .collect(Collectors.toList());
    }

    @Named("mapEventsToDomain")
    default List<ViewOrderHistory.OrderProcessingEvent> mapEventsToDomain(List<ViewOrderHistoryDTO.OrderProcessingEvent> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::mapEventToDomain)
                .collect(Collectors.toList());
    }

    default ViewOrderHistoryDTO.OrderProcessingEvent mapEventToDTO(ViewOrderHistory.OrderProcessingEvent event) {
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

    default ViewOrderHistory.OrderProcessingEvent mapEventToDomain(ViewOrderHistoryDTO.OrderProcessingEvent dto) {
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