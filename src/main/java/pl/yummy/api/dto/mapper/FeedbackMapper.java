package pl.yummy.api.dto.mapper;

import org.mapstruct.*;
import pl.yummy.api.dto.CustomerFeedbackRequestDTO;
import pl.yummy.api.dto.FeedbackDTO;
import pl.yummy.domain.Feedback;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        uses = {CourierMapper.class, OrdersMapper.class, RestaurantMapper.class}
)
public interface FeedbackMapper extends OffsetDateTimeMapper{


    // Nadpisujemy metody konwersji z OffsetDateTimeMapper, nadając im unikalne kwalifikatory,
    // aby rozwiązać konflikt (ambiguous mapping methods).
    @Override
    @Named("feedbackMapOffsetDateTimeToString")
    default String mapOffsetDateTimeToString(java.time.OffsetDateTime offsetDateTime) {
        return OffsetDateTimeMapper.super.mapOffsetDateTimeToString(offsetDateTime);
    }

    @Override
    @Named("feedbackMapStringToOffsetDateTime")
    default java.time.OffsetDateTime mapStringToOffsetDateTime(String dateTimeString) {
        return OffsetDateTimeMapper.super.mapStringToOffsetDateTime(dateTimeString);
    }

    // Mapowanie z domeny (Feedback) do DTO (FeedbackDTO)
    @Mapping(source = "date", target = "date", qualifiedByName = "feedbackMapOffsetDateTimeToString")
    // Mapujemy również zagnieżdżone pole courier.hireDate (domena: OffsetDateTime → DTO: String)
    @Mapping(source = "courier.hireDate", target = "courier.hireDate", qualifiedByName = "feedbackMapOffsetDateTimeToString")
    FeedbackDTO toDTO(Feedback feedback);

    // Mapowanie odwrotne (z FeedbackDTO do Feedback)
    @InheritInverseConfiguration
    @Mapping(source = "date", target = "date", qualifiedByName = "feedbackMapStringToOffsetDateTime")
    @Mapping(source = "courier.hireDate", target = "courier.hireDate", qualifiedByName = "feedbackMapStringToOffsetDateTime")
    Feedback toDomain(FeedbackDTO feedbackDTO);

    // Mapowanie z CustomerFeedbackRequestDTO do Feedback – zakładamy, że obiekt requestDTO nie posiada nested property courier.hireDate,
    // więc nie mapujemy tego pola.
    @Mapping(source = "date", target = "date", qualifiedByName = "feedbackMapStringToOffsetDateTime")
    Feedback toDomain(CustomerFeedbackRequestDTO requestDTO);
}
