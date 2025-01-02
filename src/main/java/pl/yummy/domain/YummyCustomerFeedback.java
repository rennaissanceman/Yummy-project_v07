package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@Builder
public class YummyCustomerFeedback {

    String orderId;
    String customerEmail;
    Integer rating; // Ocena w skali, np. 1-5
    String feedbackNote; // Komentarz klienta
    OffsetDateTime feedbackDate;
}
