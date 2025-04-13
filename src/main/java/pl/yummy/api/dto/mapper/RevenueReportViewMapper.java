package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.RevenueReportViewDTO;
import pl.yummy.domain.RevenueReportView;

import java.time.OffsetDateTime;

@Mapper(componentModel = "spring")
public interface RevenueReportViewMapper extends OffsetDateTimeMapper{

    // Nadpisanie metod konwersji dat, aby jednoznacznie korzystać z implementacji z OffsetDateTimeMapper
    @Override
    @Named("mapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return OffsetDateTimeMapper.super.mapOffsetDateTimeToString(offsetDateTime);
    }

    @Override
    @Named("mapStringToOffsetDateTime")
    default OffsetDateTime mapStringToOffsetDateTime(String dateTimeString) {
        return OffsetDateTimeMapper.super.mapStringToOffsetDateTime(dateTimeString);
    }

    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "integerToLong")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "mapOffsetDateTimeToString")
    RevenueReportViewDTO toDTO(RevenueReportView report);

    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "longToInteger")
    @Mapping(source = "startDate", target = "startDate", qualifiedByName = "mapStringToOffsetDateTime")
    @Mapping(source = "endDate", target = "endDate", qualifiedByName = "mapStringToOffsetDateTime")
    RevenueReportView toDomain(RevenueReportViewDTO dto);

    @Named("integerToLong")
    default Long integerToLong(Integer value) {
        return value == null ? null : value.longValue();
    }

    @Named("longToInteger")
    default Integer longToInteger(Long value) {
        return value == null ? null : value.intValue();
    }
}

    /*
    Wyjaśnienie:
    - Konwersja reportId:
    Używamy metod kwalifikowanych integerToLong oraz longToInteger, aby poprawnie przekonwertować typ Integer
    (w domenie) na Long (w DTO) i odwrotnie.

    - Automatyczne mapowanie:
    Pozostałe pola (startDate, endDate, totalRevenue, totalOrders, averageOrderValue) są mapowane automatycznie,
    ponieważ mają te same nazwy i typy.

    - Prostota implementacji:
    Ponieważ struktura modeli jest niemal identyczna, dodatkowe rozwiązania nie są wymagane, poza zabezpieczeniem
    konwersji reportId.


    Takie rozwiązanie jest spójne z podejściem stosowanym w innych mapperach, gdzie dodaje się mechanizmy konwersji
    typów, gdy struktura modeli nie jest całkowicie identyczna.
    */