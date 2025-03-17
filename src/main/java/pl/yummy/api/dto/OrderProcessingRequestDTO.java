package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProcessingRequestDTO {

//    Na podstawie RequestOrderProcessing
    String courierIdentifier;
    String orderNumber;
    String orderItemIdentifier;
    Integer orderItemQuantity;
    String processingCode;
    Integer processingTime;
    String comment;
    Boolean done;
//    (opcjonalnie) pola dotyczące docelowego statusu dostawy i płatności oraz czas przetwarzania
}
