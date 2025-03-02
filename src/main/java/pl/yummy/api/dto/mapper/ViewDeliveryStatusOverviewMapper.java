package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.ViewDeliveryStatusOverviewDTO;
import pl.yummy.domain.ViewDeliveryStatusOverview;

@Mapper(componentModel = "spring")
public interface ViewDeliveryStatusOverviewMapper {

    ViewDeliveryStatusOverviewDTO toDTO(ViewDeliveryStatusOverview overview);

    ViewDeliveryStatusOverview toDomain(ViewDeliveryStatusOverviewDTO dto);
}
