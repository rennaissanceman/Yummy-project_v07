package pl.yummy.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.yummy.domain.OrdersItem;
import pl.yummy.infrastructure.database.entity.OrdersItemEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        OrdersEntityMapper.class, MenuItemEntityMapper.class
})
public interface OrdersItemEntityMapper {

    OrdersItem mapFromEntity(OrdersItemEntity entity);

    OrdersItemEntity mapToEntity(OrdersItem domain);
}
