package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.ViewCustomerProfileDTO;
import pl.yummy.domain.ViewCustomerProfile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface ViewOrderDetailsMapper extends OffsetDateTimeMapper{

    @Mapping(source = "orderSummaries", target = "orderSummaries", qualifiedByName = "mapOrderSummariesToDTO")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    ViewCustomerProfileDTO toDTO(ViewCustomerProfile profile);

    @Mapping(source = "orderSummaries", target = "orderSummaries", qualifiedByName = "mapOrderSummariesToDomain")
    @Mapping(source = "ordersDateTime", target = "ordersDateTime", qualifiedByName = "mapStringToOffsetDateTime")
    ViewCustomerProfile toDomain(ViewCustomerProfileDTO dto);

    @Named("mapOrderSummariesToDTO")
    default List<ViewCustomerProfileDTO.OrderSummaryView> mapOrderSummariesToDTO(List<ViewCustomerProfile.OrderSummaryView> orders) {
        if (orders == null) {
            return Collections.emptyList();
        }
        return orders.stream()
                .map(this::mapOrderSummaryToDTO)
                .collect(Collectors.toList());
    }

    @Named("mapOrderSummaryToDTO")
    default ViewCustomerProfileDTO.OrderSummaryView mapOrderSummaryToDTO(ViewCustomerProfile.OrderSummaryView order) {
        if (order == null) {
            return null;
        }
        return ViewCustomerProfileDTO.OrderSummaryView.builder()
                .orderNumber(order.getOrderNumber())
                .ordersDateTime(order.getOrdersDateTime())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    @Named("mapOrderSummariesToDomain")
    default List<ViewCustomerProfile.OrderSummaryView> mapOrderSummariesToDomain(List<ViewCustomerProfileDTO.OrderSummaryView> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::mapOrderSummaryToDomain)
                .collect(Collectors.toList());
    }

    @Named("mapOrderSummaryToDomain")
    default ViewCustomerProfile.OrderSummaryView mapOrderSummaryToDomain(ViewCustomerProfileDTO.OrderSummaryView dto) {
        if (dto == null) {
            return null;
        }
        return ViewCustomerProfile.OrderSummaryView.builder()
                .orderNumber(dto.getOrderNumber())
                .ordersDateTime(dto.getOrdersDateTime())
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