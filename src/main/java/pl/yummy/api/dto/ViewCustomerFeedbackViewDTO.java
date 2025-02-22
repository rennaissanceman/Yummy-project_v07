package pl.yummy.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.OffsetDateTime;

@With
@Value
@Builder
public class ViewCustomerFeedbackViewDTO {

    /*
     Reprezentuje opinię klienta o restauracji lub zamówieniu.
     Przedstawia opinię klienta o restauracji lub zamówieniu. Zawiera informacje o ocenie, komentarzach oraz dacie
     dodania opinii. Taki widok umożliwia szybkie przeglądanie opinii i ocen, co może być przydatne np. na dashboardzie
     restauracji lub w module zarządzania opiniami.
     */

    Long feedbackId;
    String customerName;
    String restaurantName;
    Integer rating;
    String comments;
    OffsetDateTime date;

    public boolean isPositiveFeedback() {
        return rating != null && rating >= 4;
    }
}
