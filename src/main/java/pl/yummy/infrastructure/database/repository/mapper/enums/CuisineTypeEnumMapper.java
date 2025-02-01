package pl.yummy.infrastructure.database.repository.mapper.enums;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.enums.CuisineTypeEnumDomain;
import pl.yummy.infrastructure.database.entity.enums.CuisineTypeEnumEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CuisineTypeEnumMapper {

    @Named("toDomain")
    static CuisineTypeEnumDomain toDomain(CuisineTypeEnumEntity cuisineType) {
        if (cuisineType == null) {
            return null;
        }
        return CuisineTypeEnumDomain.valueOf(cuisineType.name());
    }

    @Named("toEntity")
    static CuisineTypeEnumEntity toEntity(CuisineTypeEnumDomain cuisineTypeEnumDomain) {
        if (cuisineTypeEnumDomain == null) {
            return null;
        }
        return CuisineTypeEnumEntity.valueOf(cuisineTypeEnumDomain.name());
    }
}
