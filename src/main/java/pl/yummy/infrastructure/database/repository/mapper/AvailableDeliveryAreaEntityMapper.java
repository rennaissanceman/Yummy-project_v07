package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.AvailableDeliveryArea;
import pl.yummy.infrastructure.database.entity.AvailableDeliveryAreaEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvailableDeliveryAreaEntityMapper {


    AvailableDeliveryArea mapFromEntity(AvailableDeliveryAreaEntity entity);
}
