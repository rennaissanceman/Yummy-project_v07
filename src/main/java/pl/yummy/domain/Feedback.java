package pl.yummy.domain;

import lombok.*;

import java.time.OffsetDateTime;


@With
@Value
@Builder
@EqualsAndHashCode(of = "feedbackId")
@ToString(of = {"feedbackId", "rating", "date"})
public class Feedback {

    Long feedbackId;
    Integer rating;
    String comments;
    OffsetDateTime date;

    // Referencja do domenowego obiektu kuriera – jeżeli opinia dotyczy kuriera
    Courier courier;

    // Referencja do domenowego obiektu zamówienia – jeżeli opinia dotyczy konkretnego zamówienia
    Orders orders;
    Restaurant restaurant;
}
