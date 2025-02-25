package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPaymentProcessingDTO {

//    Na podstawie PaymentProcessingRequest
    Long orderId;
    String orderNumber;
    String paymentMethod;
    BigDecimal amount;
    String transactionId;
    String comment;
}
