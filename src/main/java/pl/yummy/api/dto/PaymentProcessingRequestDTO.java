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
public class PaymentProcessingRequestDTO {

//    Na podstawie RequestPaymentProcessing
    Long orderId;
    String orderNumber;
    String paymentMethod;
    BigDecimal amount;
    String transactionId;
    String comment;

    // Metoda buildDefault tworzy instancję z domyślnymi wartościami
    public static PaymentProcessingRequestDTO buildDefault() {
        return PaymentProcessingRequestDTO.builder()
                .orderId(null)
                .orderNumber("")
                .paymentMethod("")
                .amount(BigDecimal.ZERO)
                .transactionId("")
                .comment("")
                .build();
    }
}
