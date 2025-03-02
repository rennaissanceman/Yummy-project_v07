package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.yummy.api.dto.RequestDeliveryAssignmentDTO;
import pl.yummy.domain.RequestDeliveryAssignment;

@Mapper(componentModel = "spring")
public interface RequestDeliveryAssignmentMapper {

    RequestDeliveryAssignmentDTO toDTO(RequestDeliveryAssignment requestDeliveryAssignment);

    RequestDeliveryAssignment toDomain(RequestDeliveryAssignmentDTO requestDeliveryAssignmentDTO);
}
