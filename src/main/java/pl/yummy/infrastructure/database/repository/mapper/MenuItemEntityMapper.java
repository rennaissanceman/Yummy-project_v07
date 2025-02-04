package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.MenuItem;
import pl.yummy.infrastructure.database.entity.MenuItemEntity;
import pl.yummy.infrastructure.database.entity.enums.DietTypeEnumEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        MenuEntityMapper.class, OrdersItemEntityMapper.class
})
public interface MenuItemEntityMapper {

    @Mapping(target = "ordersItems", ignore = true) // Ignorujemy zamówienia
    MenuItem mapFromEntity(MenuItemEntity entity);

    @Mapping(target = "ordersItems", ignore = true)
    MenuItemEntity mapToEntity(MenuItem domain);

    default Class<DietTypeEnumEntity> getDietTypeEntityClass() {
        return DietTypeEnumEntity.class;
    }
}
