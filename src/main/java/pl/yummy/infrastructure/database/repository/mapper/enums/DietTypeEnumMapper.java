package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.DietTypeEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.DietType;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DietTypeEnumMapper {

    @Named("toDomain")
    static DietTypeEnumDomain toDomain(DietType dietType) {
        if (dietType == null) {
            return null;
        }
        return DietTypeEnumDomain.valueOf(dietType.name());
    }

    @Named("toEntity")
    static DietType toEntity(DietTypeEnumDomain dietTypeEnumDomain) {
        if (dietTypeEnumDomain == null) {
            return null;
        }
        return DietType.valueOf(dietTypeEnumDomain.name());
    }
}
