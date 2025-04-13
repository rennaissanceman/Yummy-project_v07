package pl.yummy.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.yummy.api.dto.DeliveryAssignmentRequestDTO;
import pl.yummy.domain.DeliveryAssignmentRequest;

@Mapper(componentModel = "spring")
public interface DeliveryAssignmentRequestMapper extends OffsetDateTimeMapper{

    @Mapping(source = "assignmentDateTime", target = "assignmentDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    DeliveryAssignmentRequestDTO toDTO(DeliveryAssignmentRequest deliveryAssignmentRequest);

    @Mapping(source = "assignmentDateTime", target = "assignmentDateTime", qualifiedByName = "mapStringToOffsetDateTime")
    DeliveryAssignmentRequest toDomain(DeliveryAssignmentRequestDTO deliveryAssignmentRequestDTO);
}
