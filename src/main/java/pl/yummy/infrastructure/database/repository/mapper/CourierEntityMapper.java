package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Courier;
import pl.yummy.domain.enums.CourierStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.CourierEntity;
import pl.yummy.infrastructure.database.entity.enums.CourierStatusEnumEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        UserAuthEntityMapper.class
})
public interface CourierEntityMapper {

    @Mapping(target = "deliveries", ignore = true) // Ignorujemy szczegóły dostaw
    @Mapping(target = "courierStatus", source = "courierStatus", qualifiedByName = "mapStatus")
    Courier mapFromEntity(CourierEntity entity);


    @Mapping(target = "deliveries", ignore = true) // Ignorujemy szczegóły dostaw
    @Mapping(target = "courierStatus", source = "courierStatus", qualifiedByName = "mapStatusReverse")
    CourierEntity mapToEntity(Courier domain);



    @Named("mapStatus")
    static CourierStatusEnumDomain mapStatus(CourierStatusEnumEntity status) {
        if (status == null) {
            return null;
        }
        switch (status) {
            case AVAILABLE: return CourierStatusEnumDomain.AVAILABLE;
            case BUSY: return CourierStatusEnumDomain.BUSY;
            case INACTIVE: return CourierStatusEnumDomain.INACTIVE;
            default: throw new IllegalArgumentException("Unknown CourierStatus: " + status);
        }
    }

    @Named("mapStatusReverse")
    static CourierStatusEnumEntity mapStatusReverse(CourierStatusEnumDomain status) {
        if (status == null) {
            return null;
        }
        switch (status) {
            case AVAILABLE: return CourierStatusEnumEntity.AVAILABLE;
            case BUSY: return CourierStatusEnumEntity.BUSY;
            case INACTIVE: return CourierStatusEnumEntity.INACTIVE;
            default: throw new IllegalArgumentException("Unknown CourierStatus: " + status);

        }
    }


    default Class<CourierStatusEnumEntity> getCourierStatusEntityClass() {
        return CourierStatusEnumEntity.class;
    }
}
