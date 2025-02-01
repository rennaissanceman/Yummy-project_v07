package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.OrdersStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.OrdersStatusEnumEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrdersStatusEnumMapper {

    @Named("toDomain")
    static OrdersStatusEnumDomain toDomain(OrdersStatusEnumEntity ordersStatus) {
        if (ordersStatus == null) {
            return null;
        }
        return OrdersStatusEnumDomain.valueOf(ordersStatus.name());
    }

    @Named("toEntity")
    static OrdersStatusEnumEntity toEntity(OrdersStatusEnumDomain ordersStatusEnumDomain) {
        if (ordersStatusEnumDomain == null) {
            return null;
        }
        return OrdersStatusEnumEntity.valueOf(ordersStatusEnumDomain.name());
    }
}
