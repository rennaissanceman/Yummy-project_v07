package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Delivery;
import pl.yummy.infrastructure.database.entity.DeliveryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        OrdersEntityMapper.class, AvailableDeliveryAreaEntityMapper.class, CourierEntityMapper.class
})
public interface DeliveryEntityMapper {

    Delivery mapFromEntity(DeliveryEntity entity);

    DeliveryEntity mapToEntity(Delivery domain);
}
