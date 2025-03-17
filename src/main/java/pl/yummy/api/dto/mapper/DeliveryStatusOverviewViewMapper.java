package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.DeliveryStatusOverviewViewDTO;
import pl.yummy.domain.DeliveryStatusOverviewView;

@Mapper(componentModel = "spring")
public interface DeliveryStatusOverviewViewMapper {

    DeliveryStatusOverviewViewDTO toDTO(DeliveryStatusOverviewView overview);

    DeliveryStatusOverviewView toDomain(DeliveryStatusOverviewViewDTO dto);
}
