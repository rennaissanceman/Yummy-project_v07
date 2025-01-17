package pl.yummy.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "feedbackId")
@ToString(of = {"feedbackId", "customerName", "restaurantName", "rating", "comments", "date"})
public class YummyCustomerFeedback {

    // Reprezentuje opinie klientów o zamówieniach, restauracjach lub dostawcach.

    Integer feedbackId; // ID opinii
    String customerName; // Nazwa klienta
    String restaurantName; // Nazwa restauracji
    Integer rating; // Ocena (np. od 1 do 5)
    String comments; // Komentarze klienta
    OffsetDateTime date; // Data dodania opinii

    public boolean isPositiveFeedback() {
        return rating != null && rating >= 4;
    }
}
