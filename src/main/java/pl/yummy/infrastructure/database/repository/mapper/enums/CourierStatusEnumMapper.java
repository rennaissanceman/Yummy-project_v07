package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.CourierStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.CourierStatusEnumEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourierStatusEnumMapper {

    @Named("toDomain")
    static CourierStatusEnumDomain toDomain(CourierStatusEnumEntity status) {
        if (status == null) {
            return null;
        }
        switch (status) {
            case AVAILABLE:
                return CourierStatusEnumDomain.AVAILABLE;
            case BUSY:
                return CourierStatusEnumDomain.BUSY;
            case INACTIVE:
                return CourierStatusEnumDomain.INACTIVE;
            default:
                throw new IllegalArgumentException("Unknown CourierStatus: " + status);
        }
    }

    @Named("toEntity")
    static CourierStatusEnumEntity toEntity(CourierStatusEnumDomain status) {
        if (status == null) {
            return null;
        }
        switch (status) {
            case AVAILABLE:
                return CourierStatusEnumEntity.AVAILABLE;
            case BUSY:
                return CourierStatusEnumEntity.BUSY;
            case INACTIVE:
                return CourierStatusEnumEntity.INACTIVE;
            default:
                throw new IllegalArgumentException("Unknown CourierStatusEnumDomain: " + status);
        }
    }
}
