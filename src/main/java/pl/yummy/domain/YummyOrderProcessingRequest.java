package pl.yummy.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@Value
@Builder
public class YummyOrderProcessingRequest {

    String chefId;
    String orderId;
    List<OrderItemStatus> orderItemStatuses;
    String overallStatus; // e.g., "PENDING", "IN_PROGRESS", "COMPLETED", "DELIVERED"

    @With
    @Value
    @Builder
    public static class OrderItemStatus {
        String dishName;
        Integer quantity;
        String preparationStatus; // e.g., "PREPARED", "IN_PREPARATION"
        Integer preparationTime; // in minutes
        String chefId; // Optional, assigned chef
    }
}

