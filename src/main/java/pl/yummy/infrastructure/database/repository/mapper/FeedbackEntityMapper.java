package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Feedback;
import pl.yummy.infrastructure.database.entity.FeedbackEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                CourierEntityMapper.class,
                OrdersEntityMapper.class,
                RestaurantEntityMapper.class
        })
public interface FeedbackEntityMapper {

    @Mapping(source = "feedbackId", target = "feedbackId", qualifiedByName = "integerToLong")
    Feedback mapFromEntity(FeedbackEntity entity);

    @Mapping(source = "feedbackId", target = "feedbackId", qualifiedByName = "longToInteger")
    FeedbackEntity mapToEntity(Feedback domain);

    @Named("integerToLong")
    default Long integerToLong(Integer value) {
        return value == null ? null : value.longValue();
    }

    @Named("longToInteger")
    default Integer longToInteger(Long value) {
        return value == null ? null : value.intValue();
    }
}
