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
public class RequestDeliveryAssignmentRequestDTO {

//    Na podstawie DeliveryAssignmentRequest
    String orderNumber;
    String courierIdentifier;
    OffsetDateTime assignmentDateTime;
    String comment;
}
