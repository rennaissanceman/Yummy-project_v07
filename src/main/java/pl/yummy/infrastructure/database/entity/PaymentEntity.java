package pl.yummy.infrastructure.database.entity;

import pl.yummy.infrastructure.database.entity.enums.PaymentMethod;
import pl.yummy.infrastructure.database.entity.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PaymentEntity {

    private Integer paymentId;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private OffsetDateTime paymentDateTime;
}
