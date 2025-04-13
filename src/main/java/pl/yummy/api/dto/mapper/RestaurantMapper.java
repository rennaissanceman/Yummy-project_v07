package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.RestaurantDTO;
import pl.yummy.domain.Restaurant;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;

@Mapper(componentModel = "spring", uses = {OwnerMapper.class, AddressMapper.class})
public interface RestaurantMapper {

    @Mapping(source = "restaurantId", target = "restaurantIdentifier")
    @Mapping(source = "cuisineType", target = "cuisineType", qualifiedByName = "mapCuisineType")
    RestaurantDTO toDTO(Restaurant restaurant);

    @Named("mapCuisineType")
    default String mapCuisineType(CuisineTypeEnumDomain cuisineType) {
        return cuisineType == null ? null : cuisineType.name();
    }
}

    /*
    Wyjaśnienie:
    - @Mapper(componentModel = "spring", uses = {OwnerMapper.class, AddressMapper.class})
    Mapper jest zarządzany przez Springa, a dodatkowo deleguje mapowanie zagnieżdżonych obiektów (owner, address)
    do dedykowanych mapperów.

    - @Mapping dla cuisineType
    Pole cuisineType w obiekcie domenowym jest typu enum (CuisineTypeEnumDomain), natomiast w DTO oczekujemy Stringa.
    Dlatego stosujemy kwalifikowaną metodę mapCuisineType.

    - Metoda mapCuisineType
    Metoda domyślna sprawdza, czy przekazany enum jest null; jeśli nie, zwraca jego nazwę (metoda name()),
    co zapewnia spójne odwzorowanie.


    Pozostałe pola (restaurantId, restaurantName, phone, email, website, openingHours, averageRating, ratingCount,
    description, logoURL) są mapowane automatycznie, ponieważ mają zgodne nazwy i typy między domeną a DTO.
    */