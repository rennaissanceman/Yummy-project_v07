package pl.yummy.domain;

import lombok.*;
import pl.yummy.domain.enums.PaymentMethodStatusEnumDomain;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "paymentMethodId")
@ToString(of = {"paymentMethodId", "paymentMethodName", "description", "isActive", "paymentMethodStatus", "createdAt", "updatedAt"})
public class PaymentMethod {

    Long paymentMethodId;
    String paymentMethodName;
    String description;
    Boolean isActive;
    PaymentMethodStatusEnumDomain paymentMethodStatus;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
    Payment payment;
}
