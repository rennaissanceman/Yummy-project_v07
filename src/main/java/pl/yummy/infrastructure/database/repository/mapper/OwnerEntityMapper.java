package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Owner;
import pl.yummy.infrastructure.database.entity.OwnerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        UserAuthEntityMapper.class, RestaurantEntityMapper.class
})
public interface OwnerEntityMapper {

    @Mapping(target = "restaurants", ignore = true) // Ignorujemy restauracje
    Owner mapFromEntity(OwnerEntity entity);

    @Mapping(target = "restaurants", ignore = true)
    OwnerEntity mapToEntity(Owner domain);
}
