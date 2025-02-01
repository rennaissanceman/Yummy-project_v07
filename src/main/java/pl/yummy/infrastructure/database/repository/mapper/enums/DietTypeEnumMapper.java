package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.DietTypeEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.DietTypeEnumEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DietTypeEnumMapper {

    @Named("toDomain")
    static DietTypeEnumDomain toDomain(DietTypeEnumEntity dietType) {
        if (dietType == null) {
            return null;
        }
        return DietTypeEnumDomain.valueOf(dietType.name());
    }

    @Named("toEntity")
    static DietTypeEnumEntity toEntity(DietTypeEnumDomain dietTypeEnumDomain) {
        if (dietTypeEnumDomain == null) {
            return null;
        }
        return DietTypeEnumEntity.valueOf(dietTypeEnumDomain.name());
    }
}
