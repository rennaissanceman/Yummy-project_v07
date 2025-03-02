package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.ViewCustomerFeedbackDTO;
import pl.yummy.domain.ViewCustomerFeedback;

@Mapper(componentModel = "spring")
public interface ViewCustomerFeedbackMapper {

    @Mapping(source = "feedbackId", target = "feedbackId", qualifiedByName = "integerToLong")
    ViewCustomerFeedbackDTO toDTO(ViewCustomerFeedback feedback);

    @Mapping(source = "feedbackId", target = "feedbackId", qualifiedByName = "longToInteger")
    ViewCustomerFeedback toDomain(ViewCustomerFeedbackDTO dto);

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
    - Konwersja feedbackId:
    Dzięki metodom kwalifikowanym integerToLong oraz longToInteger zapewniamy poprawną konwersję między Integer a Long.

    - Standardowe mapowanie:
    Pozostałe pola (customerName, restaurantName, rating, comments, date) są mapowane automatycznie,
    ponieważ mają te same nazwy i typy w obu klasach.

    - @Mapper(componentModel = "spring")
    Mapper jest rejestrowany jako bean Springa, co ułatwia jego użycie w aplikacji.


    Takie rozwiązanie zwiększa spójność i odporność na błędy przy mapowaniu danych między warstwą domenową a DTO.
    */