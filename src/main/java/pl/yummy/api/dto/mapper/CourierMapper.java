package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.CourierDTO;
import pl.yummy.domain.Courier;
import pl.yummy.domain.enums.CourierStatusEnumDomain;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    @Mapping(source = "courierStatus", target = "courierStatus", qualifiedByName = "mapCourierStatus")
    CourierDTO toDTO( final Courier courier);

    @Named("mapCourierStatus")
    default String mapCourierStatus(final CourierStatusEnumDomain courierStatus) {
        return courierStatus == null ? null : courierStatus.name();
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