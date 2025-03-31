package pl.yummy.api.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.yummy.api.dto.CustomerFeedbackRequestDTO;
import pl.yummy.api.dto.FeedbackDTO;
import pl.yummy.domain.Feedback;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        CourierMapper.class,
        OrdersMapper.class,
        RestaurantMapper.class
})
public interface FeedbackMapper {

    FeedbackDTO toDTO(Feedback feedback);

    @InheritInverseConfiguration
    Feedback toDomain(FeedbackDTO feedbackDTO);

    // Dodajemy przeciążoną metodę dla CustomerFeedbackRequestDTO
    Feedback toDomain(CustomerFeedbackRequestDTO requestDTO);
}
