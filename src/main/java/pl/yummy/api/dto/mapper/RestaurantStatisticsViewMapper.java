package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.RestaurantStatisticsViewDTO;
import pl.yummy.domain.RestaurantStatisticsView;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantStatisticsViewMapper {

    @Mapping(source = "topSellingItems", target = "topSellingItems", qualifiedByName = "mapTopSellingItems")
    RestaurantStatisticsViewDTO toDTO(RestaurantStatisticsView statistics);

    @Mapping(source = "topSellingItems", target = "topSellingItems", qualifiedByName = "mapTopSellingItems")
    RestaurantStatisticsView toDomain(RestaurantStatisticsViewDTO dto);

    @Named("mapTopSellingItems")
    default List<String> mapTopSellingItems(List<String> items) {
        return items == null ? Collections.emptyList() : items;
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring") – Mapper jest rejestrowany jako bean Springa, co umożliwia jego łatwe
    wstrzykiwanie.

    - toDTO i toDomain – Główne metody mapujące między domenowym modelem a DTO.

    - Mapowanie listy topSellingItems:
    Metoda kwalifikowana mapTopSellingItems sprawdza, czy lista jest null. Jeśli tak, zwraca pustą listę, w przeciwnym
    razie zwraca przekazaną listę. Dzięki temu zabezpieczamy mapowanie przed ewentualnym NullPointerException.


    Takie rozwiązanie jest zgodne z podejściem stosowanym w bardziej złożonych mapperach, gdzie dodatkowe mechanizmy
    zabezpieczające mapowanie kolekcji zwiększają odporność aplikacji na błędy.
    */