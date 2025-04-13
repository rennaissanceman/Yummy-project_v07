package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.CourierDTO;
import pl.yummy.domain.Courier;
import pl.yummy.domain.enums.CourierStatusEnumDomain;

@Mapper(componentModel = "spring")
public interface CourierMapper extends OffsetDateTimeMapper{

    @Mapping(source = "courierStatus", target = "courierStatus", qualifiedByName = "mapCourierStatus")
    @Mapping(source = "hireDate", target = "hireDate", qualifiedByName = "mapOffsetDateTimeToString")
    CourierDTO toDTO(Courier courier);

    @Mapping(source = "courierStatus", target = "courierStatus", qualifiedByName = "mapStringToCourierStatus")
    @Mapping(source = "hireDate", target = "hireDate", qualifiedByName = "mapStringToOffsetDateTime")
    @Mapping(target = "userAuth", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    Courier toDomain(CourierDTO courierDTO);

    @Named("mapCourierStatus")
    default String mapCourierStatus(CourierStatusEnumDomain courierStatus) {
        return courierStatus == null ? null : courierStatus.name();
    }

    @Named("mapStringToCourierStatus")
    default CourierStatusEnumDomain mapStringToCourierStatus(String status) {
        return status == null ? null : CourierStatusEnumDomain.valueOf(status);
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa.

    - @Mapping z qualifiedByName – Mapowanie pola courierStatus odbywa się przy użyciu metody mapCourierStatus,
    która sprawdza, czy wartość jest null, a następnie zwraca jej nazwę (przy pomocy name()).

    - Metoda domyślna mapCourierStatus – Umożliwia konwersję wartości enum na String, zapewniając,
    że w przypadku wartości null zostanie zwrócone null.


    Takie rozwiązanie jest spójne z podejściem stosowanym w innych mapperach i wystarczające dla tego przypadku.
    */