package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.ViewRevenueReportDTO;
import pl.yummy.domain.ViewRevenueReport;

@Mapper(componentModel = "spring")
public interface ViewRevenueReportMapper {

    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "integerToLong")
    ViewRevenueReportDTO toDTO(ViewRevenueReport report);

    @Mapping(source = "reportId", target = "reportId", qualifiedByName = "longToInteger")
    ViewRevenueReport toDomain(ViewRevenueReportDTO dto);

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