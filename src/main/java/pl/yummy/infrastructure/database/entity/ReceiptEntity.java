package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "receiptId")
@ToString(of = {"receiptId", "order", "issueDate", "saleDate", "totalAmount", "netAmount", "taxAmount", "taxRate",
        "payment", "notes"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receipt")
public class ReceiptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id")
    private Integer receiptId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column(name = "issue_date")
    private OffsetDateTime issueDate;

    @Column(name = "sale_date")
    private OffsetDateTime saleDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "net_amount")
    private BigDecimal netAmount;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @Column(name = "tax_rate")
    private BigDecimal taxRate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentEntity payment;

    @Column(name = "notes")
    private String notes;

}
