package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.CustomerDTO;
import pl.yummy.domain.CustomerProfileView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface OrderDetailsViewMapper extends OffsetDateTimeMapper {

    @Mapping(source = "orderSummaries", target = "orderSummaries", qualifiedByName = "mapOrderSummariesToDTO")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    CustomerDTO toDTO(CustomerProfileView profile);

    @Mapping(source = "orderSummaries", target = "orderSummaries", qualifiedByName = "mapOrderSummariesToDomain")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "mapStringToOffsetDateTime")
    CustomerProfileView toDomain(CustomerDTO dto);

    @Named("mapOrderSummariesToDTO")
    default List<CustomerDTO.OrderSummaryView> mapOrderSummariesToDTO(List<CustomerProfileView.OrderSummaryView> orders) {
        if (orders == null) {
            return Collections.emptyList();
        }
        return orders.stream()
                .map(this::mapOrderSummaryToDTO)
                .collect(Collectors.toList());
    }

    @Named("mapOrderSummaryToDTO")
    default CustomerDTO.OrderSummaryView mapOrderSummaryToDTO(CustomerProfileView.OrderSummaryView order) {
        if (order == null) {
            return null;
        }
        return CustomerDTO.OrderSummaryView.builder()
                .orderNumber(order.getOrderNumber())
                // Używamy mapOffsetDateTimeToString, aby przekonwertować OffsetDateTime na String
                .ordersDateTime(mapOffsetDateTimeToString(order.getOrdersDateTime()))
                .orderStatus(order.getOrderStatus())
                .build();
    }

    @Named("mapOrderSummariesToDomain")
    default List<CustomerProfileView.OrderSummaryView> mapOrderSummariesToDomain(List<CustomerDTO.OrderSummaryView> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::mapOrderSummaryToDomain)
                .collect(Collectors.toList());
    }

    @Named("mapOrderSummaryToDomain")
    default CustomerProfileView.OrderSummaryView mapOrderSummaryToDomain(CustomerDTO.OrderSummaryView dto) {
        if (dto == null) {
            return null;
        }
        return CustomerProfileView.OrderSummaryView.builder()
                .orderNumber(dto.getOrderNumber())
                // Używamy mapStringToOffsetDateTime, aby przekonwertować String na OffsetDateTime
                .ordersDateTime(mapStringToOffsetDateTime(dto.getOrdersDateTime()))
                .orderStatus(dto.getOrderStatus())
                .build();
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring")
    Mapper jest rejestrowany jako bean Springa, co ułatwia jego użycie w aplikacji.

    - toDTO i toDomain
    Główne metody mapujące między modelem domenowym (ViewCustomerProfile) a DTO (ViewCustomerProfileDTO).
    Używają one metod kwalifikowanych do mapowania listy orderSummaries.

    - mapOrderSummariesToDTO / mapOrderSummariesToDomain
    Te metody zabezpieczają mapowanie listy – gdy źródłowa lista jest null, zwracana jest pusta lista,
    co zapobiega NullPointerException.

    - mapOrderSummaryToDTO / mapOrderSummaryToDomain
    Metody mapujące pojedynczy element RecentOrder (w naszej implementacji nazywany OrderSummaryView) między domeną
    a DTO. Korzystają z builderów, aby utworzyć nowe obiekty.

    Dzięki temu rozwiązaniu masz pewność, że nawet w przypadku braku danych kolekcji, mapper zwróci pustą listę,
    zachowując spójność danych między warstwą domenową a DTO.
    */