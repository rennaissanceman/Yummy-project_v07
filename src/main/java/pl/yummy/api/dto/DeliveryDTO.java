package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {

//    Dane dotyczące dostawy

    Long deliveryId;
    String deliveryNumber;
    String deliveryStatus;
    OffsetDateTime starTime;
    OffsetDateTime endTime;
    OffsetDateTime estimatedDeliveryTime;
    OffsetDateTime actualDeliveryDateTime;
    BigDecimal deliveryFee;
    String deliveryNotes;
}
