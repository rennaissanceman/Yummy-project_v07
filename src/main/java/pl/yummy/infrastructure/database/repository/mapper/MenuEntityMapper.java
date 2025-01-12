package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.Menu;
import pl.yummy.infrastructure.database.entity.MenuEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        RestaurantEntityMapper.class, MenuItemEntityMapper.class, OrdersEntityMapper.class
})
public interface MenuEntityMapper {

    @Mapping(target = "menuItems", ignore = true) // Ignorujemy pozycje menu
    @Mapping(target = "orders", ignore = true)    // Ignorujemy zamówienia
    Menu mapFromEntity(MenuEntity entity);

    @Mapping(target = "menuItems", ignore = true)
    @Mapping(target = "orders", ignore = true)
    MenuEntity mapToEntity(Menu domain);
}
