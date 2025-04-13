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
public class PaymentDTO {

//    Do przekazywania danych płatności
    Long paymentId;
    Long ordersId;
    String paymentMethod;
    BigDecimal amount;
    String paymentStatus;
    String transactionId;
    String createdAt;
    String updatedAt;
    String comment; //(opcjonalnie)
}
