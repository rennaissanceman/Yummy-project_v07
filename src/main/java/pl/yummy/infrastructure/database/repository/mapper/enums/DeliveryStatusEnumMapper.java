package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.DeliveryStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.DeliveryStatus;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeliveryStatusEnumMapper {

    @Named("toDomain")
    static DeliveryStatusEnumDomain toDomain(DeliveryStatus deliveryStatus) {
        if (deliveryStatus == null) {
            return null;
        }
        return DeliveryStatusEnumDomain.valueOf(deliveryStatus.name());
    }

    @Named("toEntity")
    static DeliveryStatus toEntity(DeliveryStatusEnumDomain deliveryStatusEnumDomain) {
        if (deliveryStatusEnumDomain == null) {
            return null;
        }
        return DeliveryStatus.valueOf(deliveryStatusEnumDomain.name());
    }
}
