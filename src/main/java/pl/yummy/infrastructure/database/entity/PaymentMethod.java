package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.yummy.infrastructure.database.entity.enums.PaymentMethodStatus;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "paymentMethodId")
@ToString(of = {"paymentMethodId", "paymentMethodName", "description", "isActive", "paymentMethodStatus", "createdAt", "updatedAt"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private Integer paymentMethodId;

    @Column(name = "payment_method_name")
    private String paymentMethodName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "payment_method_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodStatus paymentMethodStatus;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment_method")
    private PaymentEntity payment;
}
