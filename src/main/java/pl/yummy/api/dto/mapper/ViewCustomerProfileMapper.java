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
public interface ViewCustomerProfileMapper extends OffsetDateTimeMapper{

    @Mapping(source = "orderSummaries", target = "orderSummaries", qualifiedByName = "mapOrderSummariesToDTO")
    @Mapping(source = "orderSummaries.ordersDateTime", target = "orderSummaries.ordersDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    ViewCustomerProfileDTO toDTO(ViewCustomerProfile profile);

    @Mapping(source = "orderSummaries", target = "orderSummaries", qualifiedByName = "mapOrderSummariesToDomain")
    @Mapping(source = "orderSummaries.ordersDateTime", target = "orderSummaries.ordersDateTime", qualifiedByName = "mapStringToOffsetDateTime")
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

    @Named("mapOrderSummariesToDomain")
    default List<ViewCustomerProfile.OrderSummaryView> mapOrderSummariesToDomain(List<ViewCustomerProfileDTO.OrderSummaryView> dtos) {
        if (dtos == null) {
            return Collections.emptyList();
        }
        return dtos.stream()
                .map(this::mapOrderSummaryToDomain)
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
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co ułatwia jego wstrzykiwanie
    w innych komponentach.

    - toDTO i toDomain – Główne metody mapujące obiekt domenowy na DTO i odwrotnie.

    - Mapowanie listy orderSummaries:
    Metody mapOrderSummariesToDTO i mapOrderSummariesToDomain zabezpieczają mapowanie kolekcji – w przypadku,
    gdy źródłowa lista jest null, zwracana jest pusta lista.

    - Mapowanie pojedynczych elementów RecentOrder:
    Metody mapOrderSummaryToDTO i mapOrderSummaryToDomain mapują pojedyncze elementy, korzystając
     z builderów zdefiniowanych w zagnieżdżonych klasach.


    Takie rozwiązanie zwiększa odporność mappera na błędy (np. NullPointerException) i zapewnia spójność
    odwzorowania między modelem domenowym a DTO.
    */