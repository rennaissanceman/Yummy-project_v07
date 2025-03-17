package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.DeliveryAssignmentRequestDTO;
import pl.yummy.domain.DeliveryAssignmentRequest;

@Mapper(componentModel = "spring")
public interface DeliveryAssignmentRequestMapper {

    DeliveryAssignmentRequestDTO toDTO(DeliveryAssignmentRequest deliveryAssignmentRequest);

    DeliveryAssignmentRequest toDomain(DeliveryAssignmentRequestDTO deliveryAssignmentRequestDTO);
}
