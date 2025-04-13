package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.api.dto.CustomerDTO;
import pl.yummy.domain.CustomerProfileView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OffsetDateTimeMapper.class}
)
public interface OrderDetailsViewMapper extends OffsetDateTimeMapper {

    @Mapping(source = "orderSummaries", target = "orderSummaries", qualifiedByName = "mapOrderSummariesToDTO")
    CustomerDTO toDTO(CustomerProfileView profile);

    @Mapping(source = "orderSummaries", target = "orderSummaries", qualifiedByName = "mapOrderSummariesToDomain")
    CustomerProfileView toDomain(CustomerDTO dto);

    @Named("mapOrderSummariesToDTO")
    default List<CustomerDTO.OrderSummaryView> mapOrderSummariesToDTO(List<CustomerProfileView.OrderSummaryView> summaries) {
        if (summaries == null) {
            return Collections.emptyList();
        }
        return summaries.stream()
                .map(this::mapOrderSummaryToDTO)
                .collect(Collectors.toList());
    }

    @Named("mapOrderSummaryToDTO")
    default CustomerDTO.OrderSummaryView mapOrderSummaryToDTO(CustomerProfileView.OrderSummaryView domainSummary) {
        if (domainSummary == null) {
            return null;
        }
        return CustomerDTO.OrderSummaryView.builder()
                .orderNumber(domainSummary.getOrderNumber())
                // Konwertujemy ordersDateTime (OffsetDateTime) na String
                .ordersDateTime(mapOffsetDateTimeToString(domainSummary.getOrdersDateTime()))
                .orderStatus(domainSummary.getOrderStatus())
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
                // Konwertujemy ordersDateTime (String) na OffsetDateTime
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