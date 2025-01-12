package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Restaurant;
import pl.yummy.infrastructure.database.entity.RestaurantEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        OwnerEntityMapper.class, AddressEntityMapper.class
})
public interface RestaurantEntityMapper {

    @Mapping(target = "availableDeliveryAreas", ignore = true) // Ignorujemy obszary dostawy
    @Mapping(target = "menus", ignore = true) // Ignorujemy menu
    Restaurant mapFromEntity(RestaurantEntity entity);

    @Mapping(target = "availableDeliveryAreas", ignore = true)
    @Mapping(target = "menus", ignore = true)
    RestaurantEntity mapToEntity(Restaurant domain);
}
