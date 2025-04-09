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
public class PaymentMethodDTO {

//    Jeżeli potrzebujemy oddzielnego DTO dla metod płatności
    Long paymentMethodId;
    String paymentMethodName;
    String description;
    Boolean isActive;
    String paymentMethodStatus;
    String createdAt;
    String updatedAt;
}
