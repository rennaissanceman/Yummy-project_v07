package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.CourierStatusEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.CourierStatus;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourierStatusEnumMapper {

    @Named("toDomain")
    static CourierStatusEnumDomain toDomain(CourierStatus status) {
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
    static CourierStatus toEntity(CourierStatusEnumDomain status) {
        if (status == null) {
            return null;
        }
        switch (status) {
            case AVAILABLE:
                return CourierStatus.AVAILABLE;
            case BUSY:
                return CourierStatus.BUSY;
            case INACTIVE:
                return CourierStatus.INACTIVE;
            default:
                throw new IllegalArgumentException("Unknown CourierStatusEnumDomain: " + status);
        }
    }
}
