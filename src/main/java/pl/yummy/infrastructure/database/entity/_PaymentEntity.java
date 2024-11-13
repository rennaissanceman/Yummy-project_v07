package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.PaymentMethod;
import pl.yummy.infrastructure.database.entity.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "paymentId")
@ToString(of = {"paymentId", "paymentMethod", "amount", "paymentStatus", "paymentDateTime"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class _PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Integer paymentId;

    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_date_time", nullable = false)
    private OffsetDateTime paymentDateTime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
    private _InvoiceEntity invoice;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
    private _ReceiptEntity receipt;


}
