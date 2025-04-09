package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAssignmentRequestDTO {

//    Na podstawie RequestDeliveryAssignment
    String orderNumber;
    String courierIdentifier;
    String assignmentDateTime;
    String comment;
}
