package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

//    Dane dotyczące dostawy

    Long deliveryId;
    String deliveryNumber;
    String deliveryStatus;
    String starTime;
    String endTime;
    String estimatedDeliveryTime;
    String actualDeliveryDateTime;
    BigDecimal deliveryFee;
    String deliveryNotes;
}
