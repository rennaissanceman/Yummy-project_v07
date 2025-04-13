package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

    private Long feedbackId;
    private Integer rating;
    private String comments;
    private String date;

    // Powiązania z innymi obiektami DTO – jeśli chcesz przekazać pełne dane
    private CourierDTO courier;
    private OrdersDTO orders;
    private RestaurantDTO restaurant;
}
