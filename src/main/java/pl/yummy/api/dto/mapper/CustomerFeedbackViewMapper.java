package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.yummy.api.dto.CustomerFeedbackViewDTO;
import pl.yummy.domain.CustomerFeedbackView;

@Mapper(componentModel = "spring", uses = {OffsetDateTimeMapper.class})
public interface CustomerFeedbackViewMapper extends OffsetDateTimeMapper{

    @Mapping(source = "feedbackId", target = "feedbackId", qualifiedByName = "integerToLong")
    @Mapping(source = "date", target = "date", qualifiedByName = "mapOffsetDateTimeToString")
    CustomerFeedbackViewDTO toDTO(CustomerFeedbackView feedback);

    @Mapping(source = "feedbackId", target = "feedbackId", qualifiedByName = "longToInteger")
    CustomerFeedbackView toDomain(CustomerFeedbackViewDTO dto);

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