package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderCancellationRequestDTO {

//    Na podstawie OrderCancellationRequest
    String orderNumber;
    String cancellationReason;
}
