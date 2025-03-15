package pl.yummy.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "feedbackId")
@ToString(of = {"feedbackId", "rating", "date"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false)
    private Integer feedbackId;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "comments", length = 1000)
    private String comments;

    @Column(name = "feedback_date", nullable = false)
    private OffsetDateTime date;

    // Relacja do kuriera – jeśli opinia dotyczy kuriera.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private CourierEntity courier;

    // Relacja do zamówienia – jeśli opinia dotyczy konkretnego zamówienia.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private OrdersEntity orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;
}
