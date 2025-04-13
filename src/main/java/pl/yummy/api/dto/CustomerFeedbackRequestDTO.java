package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFeedbackRequestDTO {

    // Identyfikatory klienta i restauracji – mogą być użyte do powiązania opinii z danymi
    private Long customerId;
    private Long restaurantId;

    // Ocena opinii (np. w skali 1-5)
    private Integer rating;

    // Komentarz/opinia klienta
    private String comments;

    // Opcjonalnie: data dodania opinii – zwykle ustalana w serwisie, ale można przyjąć też z formularza
    private String date;

    // Metoda pomocnicza tworząca domyślny obiekt DTO – można ustawić domyślne wartości
    public static CustomerFeedbackRequestDTO buildDefault() {
        return CustomerFeedbackRequestDTO.builder()
                .rating(5)
                .comments("")
                .build();
    }
}
